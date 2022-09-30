package team.four.mys

import adapters.CustomRecyclerAdapterCalendar
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.databinding.ActivityCreatSubscriptionBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*


class CreateSubscriptionActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var binding: ActivityCreatSubscriptionBinding

    private lateinit var adapterCalendar: CustomRecyclerAdapterCalendar
    private lateinit var selectedDate: LocalDate

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

        selectedDate = LocalDate.now()
        setMonthView()
        autoCompleteTextView()
        fireStore()
        calendarVisibility()
        priceSpinner()
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
                        db.collection(uid())
                            .document(binding.buttonCalender.text?.trim().toString())
                            .collection("date").get()
                            .addOnSuccessListener { document ->
                                if (document.documents.isNotEmpty()) {
                                    val data = hashMapOf(
                                        "name" to binding.name.text.trim().toString(),
                                        "price" to binding.price.text?.trim().toString(),
                                        "description" to binding.description.text?.trim()
                                            .toString(),
                                        "image" to image(),
                                        "priceSpinner" to binding.priceSpinner.selectedItem.toString()
                                    )
                                    db.collection(uid()).document("price")
                                        .get()
                                        .addOnSuccessListener { doc ->
                                            var priceStart = doc.get("price")
                                            if (priceStart == null){
                                                priceStart = 0
                                            }
                                            val priceEnd =
                                                Integer.parseInt(priceStart.toString()) + Integer.parseInt(
                                                    binding.price.text.toString().trim()
                                                )
                                            val price = hashMapOf(
                                                "price" to priceEnd as Number
                                            )
                                            db.collection(uid()).document("price")
                                                .set(price)
                                        }
                                    db.collection(uid())
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
                                        "image" to image(),
                                        "priceSpinner" to binding.priceSpinner.selectedItem.toString()
                                    )
                                    db.collection(uid()).document("price")
                                        .get()
                                        .addOnSuccessListener { doc ->
                                            var priceStart = doc.get("price")
                                            if (priceStart == null){
                                                priceStart = 0
                                            }
                                            val priceEnd =
                                                Integer.parseInt(priceStart.toString()) + Integer.parseInt(
                                                    binding.price.text.toString().trim()
                                                )
                                            val price = hashMapOf(
                                                "price" to priceEnd
                                            )
                                            db.collection(uid()).document("price")
                                                .set(price)
                                        }
                                    db.collection(uid())
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

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    //url icons from storage firebase
    private fun image(): String {
        var url = ""
        when (binding.name.text.trim().toString()) {
            "Spotify" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.png?alt=media&token=89cd172f-201d-4a5e-acc6-e0da3344c26e"
            }
            "VK Combo" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/VK%20Kombo.png?alt=media&token=edc2d633-0e2b-4610-9fc0-301552bc679b"
            }
            "Netflix" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Netfix.png?alt=media&token=769f754e-3a71-44c1-ac59-86e59c7ef412"
            }
            "Apple Music" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Apple%20Music.png?alt=media&token=c4b5b8d5-4af6-4095-a332-2375daa55b8d"
            }
        }
        return url
    }

    private fun autoCompleteTextView() {
        val names = resources.getStringArray(R.array.names)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        binding.name.setAdapter(adapter)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        binding.monthYearTV.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)
        adapterCalendar = CustomRecyclerAdapterCalendar(daysInMonth) { calendarClick ->
            onItemClick(daysInMonth[calendarClick])
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this, 7)
        binding.recyclerView.adapter = adapterCalendar
        binding.recyclerView.suppressLayout(true)

        binding.buttonLeftCalendar.setOnClickListener {
            nextMonthAction()
        }
        binding.buttonRightCalendar.setOnClickListener {
            previousMonthAction()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthYearFromDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("LLLL")
        return date.format(formatter)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction() {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction() {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
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
