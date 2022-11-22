package team.four.buyupix.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import team.four.buyupix.R
import team.four.buyupix.data.db.Preferences
import team.four.buyupix.databinding.ActivityFirstBinding
import team.four.buyupix.domain.models.SetStatusBarParam
import team.four.buyupix.domain.usecases.SetStatusBarUseCase
import team.four.buyupix.domain.usecases.SetThemeUseCase
import team.four.buyupix.presentation.other.LocaleHelper

class FirstActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Preferences.init(this)
        SetThemeUseCase().execute()
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                this,
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
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