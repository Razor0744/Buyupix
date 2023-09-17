package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentSubscriptionInfoBinding
import team.four.mys.data.room.Subscription
import team.four.mys.presentation.viewmodelsfragment.SubscriptionInfoViewModel

class SubscriptionInfoFragment : Fragment() {

    private var _binding: FragmentSubscriptionInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SubscriptionInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubscriptionInfoBinding.inflate(inflater, container, false)

        binding.buttonArrowLeft.setOnClickListener {
            findNavController().navigate(R.id.home_fragment)
        }

        binding.delete.setOnClickListener {
            viewModel.deleteSubscription(
                subscription = Gson().fromJson(
                    arguments?.getString("subscription"),
                    Subscription::class.java
                )
            )
            findNavController().navigate(R.id.home_fragment)
        }

        viewModel.subscriptionInfo.observe(viewLifecycleOwner) {
            binding.name2.text = it.name
            binding.name.text = it.name
            binding.price2.text =
                getString(R.string.priceInfo, it.currencyIcon, it.price)
            binding.price.text =
                getString(R.string.priceInfo, it.currencyIcon, it.price)
            binding.description.text = it.description
            binding.category.text = it.category
            binding.switchReminder.isChecked = it.reminder
        }

        viewModel.getSubscriptionInfo(
            id =  Gson().fromJson(
                arguments?.getString("subscription"),
                Subscription::class.java
            ).id!!
        )

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}