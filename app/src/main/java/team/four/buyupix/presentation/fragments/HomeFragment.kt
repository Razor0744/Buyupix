package team.four.buyupix.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import team.four.buyupix.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.buyupix.databinding.FragmentHomeBinding
import team.four.buyupix.domain.models.SetNavigationBarParam
import team.four.buyupix.presentation.adapters.SubscriptionsAdapter
import team.four.buyupix.domain.models.SetStatusBarParam
import team.four.buyupix.domain.models.Subscriptions
import team.four.buyupix.domain.usecases.GetUIDUseCase
import team.four.buyupix.domain.usecases.SetNavigationBarUseCase
import team.four.buyupix.domain.usecases.SetStatusBarUseCase
import team.four.buyupix.presentation.activity.CreateSubscriptionActivity
import team.four.buyupix.presentation.activity.SubscriptionInfoActivity
import team.four.buyupix.presentation.viewmodelsfragment.HomeViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var subscriptions: ArrayList<Subscriptions>
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
            binding?.price?.text = getString(R.string.fullPrice, String.format("%.2f", fullPrice))
        }

        CoroutineScope(Dispatchers.IO).launch {
            fireStore()
            viewModel.fullPrice()
        }

        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                requireContext(),
                requireActivity(),
                ResourcesCompat.getColor(resources, R.color.backgroundNavBar, null)
            )
        )

        SetNavigationBarUseCase().execute(
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
        subscriptions = arrayListOf()
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
                startActivity(intent)
            }
        binding?.recyclerView?.adapter = adapterSubscriptions
    }

    private fun fireStore() {
        var i = 1
        while (i <= 31) {
            db.collection(GetUIDUseCase().execute()).document(i.toString()).collection("date")
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

            db.collection(GetUIDUseCase().execute()).document(i.toString()).collection("noDate")
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