package team.four.mys.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.R
import team.four.mys.databinding.FragmentHomeBinding
import team.four.mys.presentation.adapters.CustomRecyclerAdapterSubscriptions
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscriptions
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.domain.usecases.SetStatusBarUseCase
import team.four.mys.presentation.activity.CreateSubscriptionActivity
import team.four.mys.presentation.activity.SubscriptionInfoActivity
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel
import java.util.*

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var subscriptions: ArrayList<Subscriptions>
    private lateinit var adapterSubscriptions: CustomRecyclerAdapterSubscriptions

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

        SetStatusBarUseCase().setStatusBar(
            SetStatusBarParam(
                requireContext(),
                requireActivity(),
                requireContext().getColor(R.color.backgroundNavBar)
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
            CustomRecyclerAdapterSubscriptions(
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
            db.collection(GetUIDUseCase().getUID()).document(i.toString()).collection("date")
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

            db.collection(GetUIDUseCase().getUID()).document(i.toString()).collection("noDate")
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