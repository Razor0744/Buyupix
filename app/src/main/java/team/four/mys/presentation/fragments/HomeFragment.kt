package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
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
    private lateinit var adapterSubscriptions: SubscriptionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.createSubscription.setOnClickListener {
            findNavController().navigate(R.id.create_subscription_fragment)
        }

        binding.month.text = viewModel.date()

        viewModel.subscriptions.observe(viewLifecycleOwner) {
            subscriptions = it.sortedBy { sub -> sub.date }
            if (subscriptions.isEmpty()) visibilityFirstApp()
            adapter()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun adapter() {
        adapterSubscriptions =
            SubscriptionsAdapter(
                subscriptions,
                viewModel.date()
            ) {
                val bundle = bundleOf("subscription" to Gson().toJson(it))
                findNavController().navigate(R.id.subscription_info_fragment, bundle)
            }
        binding.recyclerView.adapter = adapterSubscriptions
    }

    private fun visibilityFirstApp() {
        binding.textView.visibility = View.VISIBLE
        binding.line3.visibility = View.VISIBLE
    }
}