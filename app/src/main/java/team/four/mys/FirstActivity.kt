package team.four.mys

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import ather.LocaleHelper
import team.four.mys.databinding.ActivityFirstBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirstActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        darkMode()
    }

    public override fun onStart() {
        super.onStart()
        // Initialize Firebase Auth
        auth = Firebase.auth
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun attachBaseContext(base: Context) {
        LocaleHelper().setLocale(base, LocaleHelper().getLanguage(base))
        super.attachBaseContext(LocaleHelper().onAttach(base))
    }

    private fun darkMode(){
        val preferences = getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val darkMode = preferences.getBoolean("DarkMode", false)
        println(darkMode)
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}