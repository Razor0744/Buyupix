package team.four.mys

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ather.LocaleHelper
import team.four.mys.databinding.ActivityFirstBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelsRoom.AlertRoom
import modelsRoom.LanguageRoom
import room.AppDatabase

class FirstActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityFirstBinding

    //Room
    private val databaseLanguage by lazy { AppDatabase.getDatabase(this).languageDao() }
    private val databaseAlert by lazy { AppDatabase.getDatabase(this).alertDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        CoroutineScope(Dispatchers.Main).launch {
            onSaveConst()
        }
    }

    private suspend fun onSaveConst(){
        val alert = AlertRoom(1, "The day before the write-off")
        val language = LanguageRoom(1, "USA")
        databaseAlert.addAlert(alert)
        databaseLanguage.addLanguage(language)
    }

    public override fun onStart() {
        super.onStart()
        // Initialize Firebase Auth
        auth = Firebase.auth
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
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
}