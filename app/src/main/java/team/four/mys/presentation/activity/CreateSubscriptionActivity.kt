package team.four.mys.presentation.activity

import android.annotation.SuppressLint
import team.four.mys.presentation.adapters.CustomRecyclerAdapterCalendar
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
import team.four.mys.R
import team.four.mys.databinding.ActivityCreatSubscriptionBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscriptions
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.domain.usecases.GetUrlImageUseCase
import team.four.mys.domain.usecases.SetStatusBarUseCase
import team.four.mys.presentation.viewmodelsactivity.CreateSubscriptionViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class CreateSubscriptionActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var binding: ActivityCreatSubscriptionBinding

    private val viewModel: CreateSubscriptionViewModel by viewModels()

    private lateinit var adapterCalendar: CustomRecyclerAdapterCalendar

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

        binding.create.setOnClickListener{

        }

        setMonthView()
        autoCompleteTextView()
        fireStore()
        calendarVisibility()
        priceSpinner()
        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                this,
                this,
                getColor(R.color.backgroundMain)
            )
        )
    }

    private fun priceSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner, R.layout.spinner_item
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.priceSpinner.adapter = adapter
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
                                        "priceSpinner" to viewModel.getPriceSpinner(binding.priceSpinner.selectedItem.toString()),
                                        "date" to binding.buttonCalender.text.toString(),
                                        "dateType" to "noDate"
                                    )
                                    db.collection(GetUIDUseCase().execute())
                                        .document(binding.priceSpinner.selectedItem.toString())
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
                                                .document(binding.priceSpinner.selectedItem.toString())
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
                                        "priceSpinner" to viewModel.getPriceSpinner(binding.priceSpinner.selectedItem.toString()),
                                        "date" to binding.buttonCalender.text.toString(),
                                        "dateType" to "date"
                                    )
                                    db.collection(GetUIDUseCase().execute())
                                        .document(binding.priceSpinner.selectedItem.toString())
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
                                                .document(binding.priceSpinner.selectedItem.toString())
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
        adapterCalendar = CustomRecyclerAdapterCalendar(daysInMonth) { calendarClick ->
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
