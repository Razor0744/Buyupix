package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.data.room.Subscription
import team.four.mys.databinding.FragmentCreateSubscriptionBinding
import team.four.mys.domain.models.Currencies
import team.four.mys.domain.usecases.CustomPositionItemDecorationUseCase
import team.four.mys.presentation.adapters.CurrenciesAdapter
import team.four.mys.presentation.viewmodelsfragment.CreateSubscriptionViewModel

class CreateSubscriptionFragment : Fragment() {

    private var _binding: FragmentCreateSubscriptionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<CreateSubscriptionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateSubscriptionBinding.inflate(inflater, container, false)

        binding.buttonArrowLeft.setOnClickListener {
            findNavController().navigate(R.id.home_fragment)
        }

        priceButtonClick()
        autoCompleteTextView()
        recyclerViewCurrenciesAdapter()
        focusOnEditView()
        addSubscription()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun addSubscription() {
        binding.create.setOnClickListener {
            if (binding.name.text.trim().toString().isNotEmpty()) {
                if (binding.price.text?.trim().toString().isNotEmpty()) {
                    if (binding.buttonCalender.text?.trim().toString() != "Write-off date") {
                        viewModel.addSubscription(
                            subscription = Subscription(
                                name = binding.name.text.trim().toString(),
                                icon = viewModel.getUrlImage(binding.name.text.trim().toString()),
                                price = binding.price.text?.trim().toString(),
                                currency = binding.priceButton.text.trim().toString(),
                                description = binding.description.text?.trim().toString(),
                                date = binding.buttonCalender.text?.trim().toString().toLong(),
                                reminder = binding.switchReminder.isChecked,
                                category = viewModel.getCategoryOfSubscription(
                                    binding.name.text.trim().toString()
                                ),
                                currencyIcon = viewModel.getCurrencyIcon(
                                    binding.priceButton.text.trim().toString()
                                )
                            )
                        )
                        findNavController().navigate(R.id.home_fragment)
                    }
                }
            }
        }
    }

    private fun priceButtonClick() {
        binding.groupCurrencies.visibility = View.INVISIBLE
        binding.priceButton.setOnClickListener {
            if (binding.groupCurrencies.visibility == View.INVISIBLE) {
                binding.priceButton.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.background_price_button_click, null
                )
                binding.priceButton.setTextColor(
                    ResourcesCompat.getColor(
                        resources, R.color.text_main, null
                    )
                )
                binding.groupCurrencies.visibility = View.VISIBLE
            } else {
                binding.priceButton.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.item_background_stroke, null
                )
                binding.priceButton.setTextColor(
                    ResourcesCompat.getColor(
                        resources, R.color.text_placeholder, null
                    )
                )
                binding.groupCurrencies.visibility = View.INVISIBLE
            }
        }
    }

    private fun recyclerViewCurrenciesAdapter() {
        val adapter = CurrenciesAdapter(currencies = currencies) {
            binding.priceButton.text = it.name
            binding.groupCurrencies.visibility = View.INVISIBLE
            binding.priceButton.background = ResourcesCompat.getDrawable(
                resources, R.drawable.item_background_stroke, null
            )
            binding.priceButton.setTextColor(
                ResourcesCompat.getColor(
                    resources, R.color.text_placeholder, null
                )
            )
        }
        val itemDecorator = ResourcesCompat.getDrawable(
            resources, R.drawable.item_decoration_price, null
        )?.let {
            CustomPositionItemDecorationUseCase(
                it
            )
        }
        binding.recyclerViewCurrencies.addItemDecoration(itemDecorator!!)
        binding.recyclerViewCurrencies.adapter = adapter
    }

    private fun focusOnEditView() {
        binding.constrainParent.setOnClickListener {
            if (binding.name.isFocused || binding.price.isFocused || binding.description.isFocused || binding.buttonCalender.isFocused) {
                val imm = requireActivity().getSystemService(
                    AppCompatActivity.INPUT_METHOD_SERVICE
                ) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.name.windowToken, 0)
            }
            binding.name.clearFocus()
            binding.price.clearFocus()
            binding.description.clearFocus()
            binding.buttonCalender.clearFocus()
            if (binding.groupCurrencies.visibility == View.VISIBLE) {
                binding.priceButton.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.item_background_stroke, null
                )
                binding.priceButton.setTextColor(
                    ResourcesCompat.getColor(
                        resources, R.color.text_placeholder, null
                    )
                )
                binding.groupCurrencies.visibility = View.INVISIBLE
            }
        }
    }

    private fun autoCompleteTextView() {
        val names = resources.getStringArray(R.array.names)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, names)
        binding.name.setAdapter(adapter)

    }

    private companion object {
        val currencies = listOf(
            Currencies("USD"),
            Currencies("EUR"),
            Currencies("BYN"),
            Currencies("AUD"),
            Currencies("AZN"),
            Currencies("ALL"),
            Currencies("DZD"),
            Currencies("BGN"),
            Currencies("BRL"),
            Currencies("RUB")
        )
    }
}