package team.four.mys.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentHomeBinding
import team.four.mys.domain.models.SetNavigationColorParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscription
import team.four.mys.presentation.activity.CreateSubscriptionActivity
import team.four.mys.presentation.activity.SubscriptionInfoActivity
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
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }

        binding.month.text = viewModel.date()

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundNavBar, null)
            )
        )

        viewModel.setNavigationColor(
            SetNavigationColorParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundNavBar, null)
            )
        )

        viewModel.subscriptions.observe(viewLifecycleOwner) {
            subscriptions = it.sortedBy { sub -> sub.date }
            if (subscriptions.isEmpty()) visibilityFirstApp()
            adapter()
        }

        viewModel.getSubscriptions()

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
                val intent = Intent(requireContext(), SubscriptionInfoActivity::class.java)
                intent.putExtra("subscription", Gson().toJson(it))
                startActivity(intent)
            }
        binding.recyclerView.adapter = adapterSubscriptions
    }

    private fun visibilityFirstApp() {
        binding.textView.visibility = View.VISIBLE
        binding.line3.visibility = View.VISIBLE
    }
}