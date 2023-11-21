package team.four.mys.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentCreateSubscriptionBinding
import team.four.mys.domain.models.Currencies
import team.four.mys.data.room.Subscription
import team.four.mys.domain.usecases.CustomPositionItemDecorationUseCase
import team.four.mys.presentation.adapters.CalendarAdapter
import team.four.mys.presentation.adapters.CurrenciesAdapter
import team.four.mys.presentation.viewmodelsfragment.CreateSubscriptionViewModel
import java.time.LocalDate

class CreateSubscriptionFragment : Fragment() {

    private var _binding: FragmentCreateSubscriptionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<CreateSubscriptionViewModel>()

    private var selectedDate: LocalDate = LocalDate.now()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateSubscriptionBinding.inflate(inflater, container, false)

        binding.buttonArrowLeft.setOnClickListener {
            findNavController().navigate(R.id.home_fragment)
        }

        setMonthView()
        priceButtonClick()
        autoCompleteTextView()
        calendarVisibility()
        recyclerViewCurrenciesAdapter()
        focusOnEditView()
        addSubscription()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable.add(viewModel.getCurrencies()
            .subscribe({
                       Log.i("RETROFIT", it.Valute.toString())
            }, {
                Log.e("RETROFIT", it.toString())
            })
        )
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

    private fun calendarVisibility() {
        binding.groupCalendar.visibility = View.INVISIBLE
        binding.calendarVis.setOnClickListener {
            if (binding.groupCalendar.visibility == View.INVISIBLE) {
                binding.groupCalendar.visibility = View.VISIBLE
                binding.buttonCalender.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_calendar_click, null),
                    null
                )
            } else {
                binding.groupCalendar.visibility = View.INVISIBLE
                binding.buttonCalender.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_calendar, null),
                    null
                )
            }
        }
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
            if (binding.groupCalendar.visibility == View.VISIBLE) {
                binding.groupCalendar.visibility = View.INVISIBLE
                binding.buttonCalender.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_calendar, null),
                    null
                )
            }
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

    private fun setMonthView() {
        binding.monthYearTv.text = viewModel.monthYearFromDate(selectedDate)
        val daysInMonth = viewModel.daysInMonthArray(selectedDate)
        val adapterCalendar = CalendarAdapter(daysInMonth) { calendarClick ->
            onItemClick(daysInMonth[calendarClick])
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.recyclerView.adapter = adapterCalendar
        binding.recyclerView.suppressLayout(true)

        binding.buttonLeftCalendar.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }
        binding.buttonRightCalendar.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }
    }

    private fun onItemClick(dayText: String) {
        if (dayText != "") {
            binding.buttonCalender.setText(dayText)
            binding.groupCalendar.visibility = View.INVISIBLE
            binding.buttonCalender.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ResourcesCompat.getDrawable(resources, R.drawable.ic_calendar, null),
                null
            )
        }
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