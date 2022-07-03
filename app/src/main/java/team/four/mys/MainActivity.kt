package team.four.mys

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ather.LocaleHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fragments.HomeFragment
import fragments.SettingsFragment
import fragments.StatisticsFragment
import team.four.mys.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var alert: String

    //fragments
    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment)
                }
                R.id.statistics -> {
                    replaceFragment(statisticsFragment)
                }
                R.id.settings -> {
                    settingsFragment()
                    replaceFragment(settingsFragment)
                }
            }
            true
        }

        replaceFragment(homeFragment)
        alert()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    override fun attachBaseContext(base: Context) {
        LocaleHelper().setLocale(base, LocaleHelper().getLanguage(base))
        super.attachBaseContext(LocaleHelper().onAttach(base))
    }

    private fun settingsFragment() {
        val bundle = Bundle()
        bundle.putString("alert", alert)
        settingsFragment.arguments = bundle
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun alert() {
        db = FirebaseFirestore.getInstance()
        db.collection(uid()).document("alert").get()
            .addOnSuccessListener { document ->
                val alertDocument = document.get("alert")
                if (alertDocument == null) {
                    val data = hashMapOf("alert" to "The day before the write-off")
                    db.collection(uid()).document("alert").set(data)
                } else {
                    alert = alertDocument as String
                }
            }
    }
}