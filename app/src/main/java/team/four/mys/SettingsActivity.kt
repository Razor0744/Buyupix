package team.four.mys

import android.content.Context
import android.content.Intent
import fragments.NavigationFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ather.LocaleHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import team.four.mys.databinding.ActivitySettingsBinding
import java.util.*


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var locale: String
    private lateinit var alert: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.alert.setOnClickListener {
        //    val intent = Intent(this, AlertActivity::class.java)
        //  intent.putExtra("alert", alert)
        //startActivity(intent)
        //}

        //binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
        //  if (isChecked) {
        //    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        //  delegate.applyDayNight()
        //}
        //}

        //binding.language.setOnClickListener {
        //  locale = Locale.getDefault().language
        //val intent = Intent(this, LanguageActivity::class.java)
        //intent.putExtra("locale", locale)
        //startActivity(intent)
        //}

        //alert()
        //navigationFragment()
    }

    override fun attachBaseContext(base: Context) {
        LocaleHelper().setLocale(base, LocaleHelper().getLanguage(base))
        super.attachBaseContext(LocaleHelper().onAttach(base))
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

    private fun navigationFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NavigationFragment()
        val bundle = Bundle()
        bundle.putInt("i", 3)
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment, fragment).commit()
    }
}