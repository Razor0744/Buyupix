package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivityCreatSubscriptionBinding
import team.four.mys.domain.models.Currencies
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscription
import team.four.mys.domain.usecases.CustomPositionItemDecorationUseCase
import team.four.mys.presentation.adapters.CalendarAdapter
import team.four.mys.presentation.adapters.CurrenciesAdapter
import team.four.mys.presentation.viewmodelsactivity.CreateSubscriptionViewModel
import java.time.LocalDate


class CreateSubscriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatSubscriptionBinding

    private val viewModel by viewModel<CreateSubscriptionViewModel>()

    private lateinit var adapterCalendar: CalendarAdapter

    private var selectedDate: LocalDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatSubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "HomeFragment")
            startActivity(intent)
        }

        setMonthView()
        priceButtonClick()
        autoCompleteTextView()
        calendarVisibility()
        recyclerViewCurrenciesAdapter()
        focusOnEditView()
        addSubscription()

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getCurrencies()
        }
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
                                reminder = binding.switchReminder.isActivated,
                                category = viewModel.getCategoryOfSubscription(
                                    binding.name.text.trim().toString()
                                ),
                                currencyIcon = viewModel.getCurrencyIcon(
                                    binding.priceButton.text.trim().toString()
                                )
                            )
                        )
                        startActivity(Intent(this, MainActivity::class.java))
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
                        resources, R.color.textMain, null
                    )
                )
                binding.groupCurrencies.visibility = View.VISIBLE
            } else {
                binding.priceButton.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.item_background_stroke, null
                )
                binding.priceButton.setTextColor(
                    ResourcesCompat.getColor(
                        resources, R.color.textPlaceholder, null
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
                    resources, R.color.textPlaceholder, null
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
                val imm = getSystemService(
                    INPUT_METHOD_SERVICE
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
                        resources, R.color.textPlaceholder, null
                    )
                )
                binding.groupCurrencies.visibility = View.INVISIBLE
            }
        }
    }

    private fun autoCompleteTextView() {
        val names = resources.getStringArray(R.array.names)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        binding.name.setAdapter(adapter)

    }

    private fun setMonthView() {
        binding.monthYearTV.text = viewModel.monthYearFromDate(selectedDate)
        val daysInMonth = viewModel.daysInMonthArray(selectedDate)
        adapterCalendar = CalendarAdapter(daysInMonth) { calendarClick ->
            onItemClick(daysInMonth[calendarClick])
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this, 7)
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

    companion object {
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