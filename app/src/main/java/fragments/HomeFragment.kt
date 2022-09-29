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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import models.Subscriptions
import team.four.mys.CreateSubscriptionActivity
import team.four.mys.R
import team.four.mys.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var subscriptions: ArrayList<Subscriptions>
    private lateinit var adapterSubscriptions: CustomRecyclerAdapterSubscriptions

    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        subscriptions = arrayListOf()
        adapterSubscriptions =
            CustomRecyclerAdapterSubscriptions(requireContext(), subscriptions, date())
        binding?.recyclerView?.adapter = adapterSubscriptions

        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }

        CoroutineScope(IO).launch {
            fireStore()
            setPrice()
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
        db = FirebaseFirestore.getInstance()
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

    @SuppressLint("SetTextI18n")
    private fun setPrice() {
        db = FirebaseFirestore.getInstance()
        db.collection(uid()).document("price")
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    binding?.price?.text = "${doc.get("price").toString()} USD"
                } else {
                    binding?.price?.text = "0 USD"
                }
            }
    }

    private fun date(): String {
        val month: String = SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
        binding?.month?.text = month
        return month
    }
}