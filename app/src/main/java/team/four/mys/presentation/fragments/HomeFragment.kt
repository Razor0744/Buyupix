package team.four.mys.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentHomeBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscriptions
import team.four.mys.presentation.activity.CreateSubscriptionActivity
import team.four.mys.presentation.activity.SubscriptionInfoActivity
import team.four.mys.presentation.adapters.SubscriptionsAdapter
import team.four.mys.domain.usecases.SetNavigationColorUseCase
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel by viewModel<HomeViewModel>()

    private val subscriptions: ArrayList<Subscriptions> = arrayListOf()
    private lateinit var adapterSubscriptions: SubscriptionsAdapter

    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }

        binding?.month?.text = viewModel.date()

        viewModel.fullPrice.observe(viewLifecycleOwner) { fullPrice ->
            if (fullPrice == 0f) {
                visibilityFirstApp()
            }
            binding?.price?.text = getString(R.string.fullPrice, String.format("%.2f", fullPrice))
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fullPrice()
            fireStore()
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundNavBar, null)
            )
        )

        SetNavigationColorUseCase().execute(
            SetNavigationBarParam(
                requireActivity(),
                ResourcesCompat.getColor(resources, R.color.backgroundNavBar, null)
            )
        )

        adapter()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun adapter() {
        adapterSubscriptions =
            SubscriptionsAdapter(
                requireContext(),
                subscriptions,
                viewModel.date()
            ) { subscriptionsClick ->
                val intent = Intent(context, SubscriptionInfoActivity::class.java)
                intent.putExtra("name", subscriptionsClick.name)
                intent.putExtra("date", subscriptionsClick.date)
                intent.putExtra("dateType", subscriptionsClick.dateType)
                intent.putExtra("priceName", subscriptionsClick.priceName)
                intent.putExtra("price", subscriptionsClick.price)
                startActivity(intent)
            }
        binding?.recyclerView?.adapter = adapterSubscriptions
    }

    private fun fireStore() {
        if (subscriptions.isEmpty()) {
            var i = 1
            while (i <= 31) {
                db.collection(viewModel.getUID()).document(i.toString()).collection("date")
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

                db.collection(viewModel.getUID()).document(i.toString()).collection("noDate")
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
    }

    private fun visibilityFirstApp() {
        binding?.textView?.visibility = View.VISIBLE
        binding?.line3?.visibility = View.VISIBLE
    }
}