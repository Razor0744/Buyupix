package team.four.mys

import adapters.CustomRecyclerAdapterCalendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.databinding.ActivityCreatSubscriptionBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


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

//        binding.calendarView.root.visibility = View.INVISIBLE
//        binding.buttonCalender.setOnClickListener {
//            if (binding.calendarView.root.visibility == View.INVISIBLE) {
//                binding.calendarView.root.visibility = View.VISIBLE
//            } else {
//                binding.calendarView.root.visibility = View.INVISIBLE
//            }
//        }

        Glide
            .with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.png?alt=media&token=89cd172f-201d-4a5e-acc6-e0da3344c26e")
            .into(binding.imageOfSubscription)

        binding.create.setOnClickListener {
            if (binding.name.text.trim().toString().isNotEmpty()) {
                if (binding.price.text.trim().toString().isNotEmpty()) {
                    db.collection(uid()).document()
                    val data = hashMapOf(
                        "name" to binding.name.text.trim().toString(),
                        "price" to binding.price.text.trim().toString(),
                        "description" to binding.description.text.trim().toString()
                    )
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

        selectedDate = LocalDate.now()
        setMonthView()
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    //url icons from storage firebase
    private fun icons(): String {
        var url = ""
        when (binding.nameActivity.text) {
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        binding.monthYearTV.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)
        adapterCalendar = CustomRecyclerAdapterCalendar(daysInMonth)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapterCalendar
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
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction(view: View?) {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction(view: View?) {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onItemClick(position: Int, dayText: String) {
        if (dayText != "") {
            val message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
