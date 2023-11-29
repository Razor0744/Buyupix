package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        lifecycleScope.launch(Dispatchers.IO) {
            getSubscriptions()
        }

        return binding.root
    }

    private suspend fun getSubscriptions() {
        viewModel.getSubscriptions()
            .catch { println(it) }
            .collect {
                withContext(Dispatchers.Main) {
                    if (it.isNotEmpty()) visibilityFirstApp()
                    adapter(it.sortedBy { sub -> sub.date })
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun adapter(subscriptions: List<Subscription>) {
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
        )?.let { dividerItemDecoration.setDrawable(it) }
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.adapter = adapterSubscriptions
    }

    private fun visibilityFirstApp() {
        binding.doNotHaveSubscriptionLinearLayout.visibility = View.GONE
        binding.searchEditText.visibility = View.VISIBLE
        binding.createSubscriptionButton.visibility = View.VISIBLE
    }
}