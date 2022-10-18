package fragments

import adapters.CustomRecyclerAdapterSubscriptions
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import api.retrofit.Currencies
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.domain.models.Rates
import team.four.mys.domain.models.Subscriptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.four.mys.CreateSubscriptionActivity
import team.four.mys.R
import team.four.mys.SubscriptionInfoActivity
import team.four.mys.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var subscriptions: ArrayList<Subscriptions>
    private lateinit var adapterSubscriptions: CustomRecyclerAdapterSubscriptions

    private var db = Firebase.firestore

    //Price
    private var EUR: Float? = null
    private var BYN: Float? = null
    private var priceUSD: Int? = null
    private var priceBYN: Int? = null
    private var priceEUR: Int? = null
    private var fullPrice: Float? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        subscriptions = arrayListOf()
        adapterSubscriptions =
            CustomRecyclerAdapterSubscriptions(
                requireContext(),
                subscriptions,
                date()
            ) { subscriptionsClick ->
                val intent = Intent(context, SubscriptionInfoActivity::class.java)
                intent.putExtra("name", subscriptionsClick.name)
                intent.putExtra("date", subscriptionsClick.date)
                intent.putExtra("dateType", subscriptionsClick.dateType)
                startActivity(intent)
            }
        binding?.recyclerView?.adapter = adapterSubscriptions

        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }

        CoroutineScope(Dispatchers.IO).launch {
            fireStore()
            fullPrice()
        }
        statusBar()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun statusBar() {
        requireActivity().window.statusBarColor =
            requireContext().getColor(R.color.backgroundNavBar)
        val preferences = activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val darkMode = preferences?.getBoolean("DarkMode", false)
        if (darkMode == true) {
            requireActivity().window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        } else {
            requireActivity().window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun fireStore() {
        var i = 1
        while (i <= 31) {
            db.collection(uid()).document(i.toString()).collection("date")
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            return
                        }

                        for (dc: DocumentChange in value?.documentChanges!!) {
                            if (dc.type == DocumentChange.Type.ADDED) {
                                subscriptions.add(dc.document.toObject(Subscriptions::class.java))
                            }
                        }
                        adapterSubscriptions.notifyDataSetChanged()
                    }

                })

            db.collection(uid()).document(i.toString()).collection("noDate")
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            return
                        }

                        for (dc: DocumentChange in value?.documentChanges!!) {
                            if (dc.type == DocumentChange.Type.ADDED) {
                                subscriptions.add(dc.document.toObject(Subscriptions::class.java))
                            }
                        }
                        adapterSubscriptions.notifyDataSetChanged()
                    }
                })
            i++
        }
    }

    private fun fullPrice() {
        getPrice("USD")
        getPrice("BYN")
        getPrice("EUR")
        retrofit()
        while (true) {
            if (EUR != null && BYN != null && priceBYN != null && priceUSD != null && priceEUR != null) {
                activity?.runOnUiThread {
                    fullPrice = priceUSD!! + (priceBYN!! / BYN!!) + (priceEUR!! / EUR!!)
                    binding?.price?.text = fullPrice.toString()
                }
                break
            }
        }
    }

    private fun retrofit() {
        Currencies.retrofitService.getRates().enqueue(object : Callback<Rates> {
            override fun onResponse(call: Call<Rates>, response: Response<Rates>) {
                val responses = response.body() as Rates
                EUR = responses.rates?.EUR
                BYN = responses.rates?.BYN
            }

            override fun onFailure(call: Call<Rates>, t: Throwable) {
                println(t)
            }
        })
    }

    private fun getPrice(string: String) {
        db.collection(uid()).document(string)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    when (string) {
                        "USD" -> priceUSD = Integer.parseInt(doc.get("price").toString())
                        "BYN" -> priceBYN = Integer.parseInt(doc.get("price").toString())
                        "EUR" -> priceEUR = Integer.parseInt(doc.get("price").toString())
                    }
                } else {
                    when (string) {
                        "USD" -> priceUSD = 0
                        "BYN" -> priceBYN = 0
                        "EUR" -> priceEUR = 0
                    }
                }
            }
    }

    private fun date(): String {
        val month: String = SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
        binding?.month?.text = month
        return month
    }
}