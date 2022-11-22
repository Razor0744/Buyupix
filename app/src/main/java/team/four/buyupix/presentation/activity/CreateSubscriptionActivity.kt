package team.four.buyupix.presentation.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.buyupix.R
import team.four.buyupix.data.repository.CurrenciesData
import team.four.buyupix.databinding.ActivityCreatSubscriptionBinding
import team.four.buyupix.domain.models.SetStatusBarParam
import team.four.buyupix.domain.usecases.GetPriceSpinnerUseCase
import team.four.buyupix.domain.usecases.GetUIDUseCase
import team.four.buyupix.domain.usecases.GetUrlImageUseCase
import team.four.buyupix.domain.usecases.SetStatusBarUseCase
import team.four.buyupix.presentation.adapters.CalendarAdapter
import team.four.buyupix.presentation.adapters.CurrenciesAdapter
import team.four.buyupix.presentation.other.CustomPositionItemDecoration
import team.four.buyupix.presentation.viewmodelsactivity.CreateSubscriptionViewModel
import java.time.LocalDate
import java.util.*


class CreateSubscriptionActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var binding: ActivityCreatSubscriptionBinding

    private val viewModel by viewModels<CreateSubscriptionViewModel>()

    private lateinit var adapterCalendar: CalendarAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    private var selectedDate: LocalDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
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
        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                this,
                this,
                getColor(R.color.backgroundMain)
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
        val adapter = CurrenciesAdapter(currencies = CurrenciesData.currencies) {
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
                        db.collection(GetUIDUseCase().execute())
                            .document(binding.buttonCalender.text?.trim().toString())
                            .collection("date").get()
                            .addOnSuccessListener { document ->
                                if (document.documents.isNotEmpty()) {
                                    val data = hashMapOf(
                                        "name" to binding.name.text.trim().toString(),
                                        "price" to binding.price.text?.trim().toString(),
                                        "description" to binding.description.text?.trim()
                                            .toString(),
                                        "image" to GetUrlImageUseCase().execute(
                                            binding.name.toString().trim()
                                        ),
                                        "priceSpinner" to GetPriceSpinnerUseCase().execute(
                                            binding.priceButton.text.toString()
                                        ),
                                        "date" to binding.buttonCalender.text.toString(),
                                        "dateType" to "noDate"
                                    )
                                    db.collection(GetUIDUseCase().execute())
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
                                            db.collection(GetUIDUseCase().execute())
                                                .document(binding.priceButton.text.toString())
                                                .set(price)
                                        }
                                    db.collection(GetUIDUseCase().execute())
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
                                            binding.name.toString().trim()
                                        ),
                                        "priceSpinner" to GetPriceSpinnerUseCase().execute(
                                            binding.priceButton.text.toString()
                                        ),
                                        "date" to binding.buttonCalender.text.toString(),
                                        "dateType" to "date"
                                    )
                                    db.collection(GetUIDUseCase().execute())
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
                                            db.collection(GetUIDUseCase().execute())
                                                .document(binding.priceButton.text.toString())
                                                .set(price)
                                        }
                                    db.collection(GetUIDUseCase().execute())
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

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun onItemClick(dayText: String) {
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