package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.data.objects.ObjectsData.currencies
import team.four.mys.databinding.ActivityCreatSubscriptionBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.GetPriceSpinnerUseCase
import team.four.mys.domain.usecases.GetUrlImageUseCase
import team.four.mys.presentation.adapters.CalendarAdapter
import team.four.mys.presentation.adapters.CurrenciesAdapter
import team.four.mys.presentation.other.CustomPositionItemDecoration
import team.four.mys.presentation.viewmodelsactivity.CreateSubscriptionViewModel
import java.time.LocalDate


class CreateSubscriptionActivity : AppCompatActivity() {

    private val db = Firebase.firestore

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
        fireStore()
        calendarVisibility()
        recyclerViewCurrenciesAdapter()

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
    }

    private fun priceButtonClick() {
        binding.groupCurrencies.visibility = View.INVISIBLE
        binding.priceButton.setOnClickListener {
            if (binding.groupCurrencies.visibility == View.INVISIBLE) {
                binding.priceButton.background =
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.background_price_button_click,
                        null
                    )
                binding.priceButton.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.textMain,
                        null
                    )
                )
                binding.groupCurrencies.visibility = View.VISIBLE
            } else {
                binding.priceButton.background =
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.item_background_stroke,
                        null
                    )
                binding.priceButton.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.textPlaceholder,
                        null
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
            binding.priceButton.background =
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.item_background_stroke,
                    null
                )
            binding.priceButton.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.textPlaceholder,
                    null
                )
            )
        }
        val itemDecorator = ResourcesCompat.getDrawable(
            resources,
            R.drawable.item_decoration_price,
            null
        )?.let {
            CustomPositionItemDecoration(
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
        binding.constrainParent.setOnClickListener {
            binding.name.clearFocus()
            binding.price.clearFocus()
            binding.description.clearFocus()
            binding.buttonCalender.clearFocus()
            val imm = getSystemService(
                INPUT_METHOD_SERVICE
            ) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
            if (binding.groupCalendar.visibility == View.VISIBLE) {
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

    private fun fireStore() {
        binding.create.setOnClickListener {
            if (binding.name.text.trim().toString().isNotEmpty()) {
                if (binding.price.text?.trim().toString().isNotEmpty()) {
                    if (binding.buttonCalender.text?.trim().toString() != "Write-off date") {
                        db.collection(viewModel.getUID())
                            .document(binding.buttonCalender.text?.trim().toString())
                            .collection("date").get()
                            .addOnSuccessListener {
                                if (it.documents.isNotEmpty()) {
                                    val data = hashMapOf(
                                        "name" to binding.name.text.trim().toString(),
                                        "price" to binding.price.text?.trim().toString(),
                                        "description" to binding.description.text?.trim()
                                            .toString(),
                                        "image" to GetUrlImageUseCase().execute(
                                            binding.name.text.trim().toString()
                                        ),
                                        "priceSpinner" to GetPriceSpinnerUseCase().execute(
                                            binding.priceButton.text.toString()
                                        ),
                                        "date" to binding.buttonCalender.text.toString(),
                                        "dateType" to "noDate"
                                    )
                                    db.collection(viewModel.getUID())
                                        .document(binding.priceButton.text.toString())
                                        .get()
                                        .addOnSuccessListener { doc ->
                                            var priceStart = doc.get("price")
                                            if (priceStart == null) {
                                                priceStart = 0
                                            }
                                            val priceEnd =
                                                Integer.parseInt(priceStart.toString()) + Integer.parseInt(
                                                    binding.price.text.toString().trim()
                                                )
                                            val price = hashMapOf(
                                                "price" to priceEnd as Number
                                            )
                                            db.collection(viewModel.getUID())
                                                .document(binding.priceButton.text.toString())
                                                .set(price)
                                        }
                                    db.collection(viewModel.getUID())
                                        .document(binding.buttonCalender.text?.trim().toString())
                                        .collection("noDate")
                                        .document(binding.name.text.trim().toString()).set(data)
                                    startActivity(Intent(this, MainActivity::class.java))
                                } else {
                                    val data = hashMapOf(
                                        "name" to binding.name.text.trim().toString(),
                                        "price" to binding.price.text?.trim().toString(),
                                        "description" to binding.description.text?.trim()
                                            .toString(),
                                        "writeOffDate" to binding.buttonCalender.text?.trim()
                                            .toString(),
                                        "image" to GetUrlImageUseCase().execute(
                                            binding.name.text.trim().toString()
                                        ),
                                        "priceSpinner" to GetPriceSpinnerUseCase().execute(
                                            binding.priceButton.text.toString()
                                        ),
                                        "date" to binding.buttonCalender.text.toString(),
                                        "dateType" to "date"
                                    )
                                    db.collection(viewModel.getUID())
                                        .document(binding.priceButton.text.toString())
                                        .get()
                                        .addOnSuccessListener { doc ->
                                            var priceStart = doc.get("price")
                                            if (priceStart == null) {
                                                priceStart = 0
                                            }
                                            val priceEnd =
                                                Integer.parseInt(priceStart.toString()) + Integer.parseInt(
                                                    binding.price.text.toString().trim()
                                                )
                                            val price = hashMapOf(
                                                "price" to priceEnd
                                            )
                                            db.collection(viewModel.getUID())
                                                .document(binding.priceButton.text.toString())
                                                .set(price)
                                        }
                                    db.collection(viewModel.getUID())
                                        .document(binding.buttonCalender.text?.trim().toString())
                                        .collection("date")
                                        .document(binding.name.text.trim().toString()).set(data)
                                    startActivity(Intent(this, MainActivity::class.java))
                                }
                            }
                    } else {
                        Toast.makeText(
                            this,
                            "Не, ну деньги я полагаю у тебя каждый день списывают",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Бля, ты конечно приколист, а цену я выдумывать должен?",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(this, "Ты ебаны рот название напиши", Toast.LENGTH_LONG).show()
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
}