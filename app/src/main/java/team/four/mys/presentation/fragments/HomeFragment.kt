package team.four.mys.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.data.room.Subscription
import team.four.mys.databinding.FragmentHomeBinding
import team.four.mys.presentation.adapters.SubscriptionsAdapter
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var subscriptions: List<Subscription>

    val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.createSubscriptionButton2.setOnClickListener {
            findNavController().navigate(R.id.create_subscription_fragment)
        }

        binding.createSubscriptionButton.setOnClickListener {
            findNavController().navigate(R.id.create_subscription_fragment)
        }

//        viewModel.subscriptions.observe(viewLifecycleOwner) {
//            subscriptions = it.sortedBy { sub -> sub.date }
//            if (subscriptions.isNotEmpty()) visibilityFirstApp()
//            adapter()
//        }
        val disposable = viewModel.getSubscriptions
            .subscribe(
                {
                    subscriptions = it.sortedBy { sub -> sub.date }
                    if (subscriptions.isNotEmpty()) visibilityFirstApp()
                    adapter()
                    Log.i("RX", it.toString())
                },
                { Log.i("RX", it.toString()) }
            )
        compositeDisposable.add(disposable)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        compositeDisposable.dispose()
    }

    private fun adapter() {
        val adapterSubscriptions =
            SubscriptionsAdapter(
                subscriptions = subscriptions,
                month = viewModel.date()
            ) {
                val bundle = bundleOf("subscription" to Gson().toJson(it))
                findNavController().navigate(R.id.subscription_info_fragment, bundle)
            }
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            VERTICAL
        )
        ResourcesCompat.getDrawable(
            resources,
            R.drawable.item_decoration_recycler_view_subscription,
            null
        )
            ?.let { dividerItemDecoration.setDrawable(it) }
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.adapter = adapterSubscriptions
    }

    private fun visibilityFirstApp() {
        binding.doNotHaveSubscriptionLinearLayout.visibility = View.GONE
        binding.searchEditText.visibility = View.VISIBLE
        binding.createSubscriptionButton.visibility = View.VISIBLE
    }
}