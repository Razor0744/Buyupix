package team.four.mys

import android.content.Intent
import fragments.NavigationFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import team.four.mys.databinding.ActivitySettingsBinding
import java.util.*


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var locale: String
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
        }

        binding.language.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            intent.putExtra("locale", locale)
            startActivity(intent)
        }

        number()
        languageDataBase()
    }

    private fun languageDataBase() {
        db = FirebaseFirestore.getInstance()

        locale = Locale.getDefault().language
        val data: MutableMap<String, String> = mutableMapOf("language" to locale)
        db.collection(uid()).document("language").get()
            .addOnSuccessListener { document ->
                val languageData = document.get("language")
                if (languageData == null) {
                    db.collection(uid()).document("language").set(data)
                } else {
                    locale = document.get("language") as String
                    println(locale)
                    println("locale")
                }
            }
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun number(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NavigationFragment()
        val bundle = Bundle()
        bundle.putInt("i", 3)
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment, fragment).commit()
    }
}