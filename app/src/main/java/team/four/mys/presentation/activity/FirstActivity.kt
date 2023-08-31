package team.four.mys.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivityFirstBinding
import team.four.mys.domain.models.SetNavigationColorParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.LocaleHelperUseCase
import team.four.mys.presentation.viewmodelsactivity.FirstActivityViewModel

class FirstActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityFirstBinding

    private val viewModel by viewModel<FirstActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.setTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.setOnClickListener {
            startActivity(Intent(this, AuthenticationActivity::class.java))
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        viewModel.setNavigationColor(
            SetNavigationColorParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
    }

    override fun onStart() {
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
        LocaleHelperUseCase().setLocale(base, LocaleHelperUseCase().getLanguage(base))
        super.attachBaseContext(LocaleHelperUseCase().onAttach(base))
    }
}