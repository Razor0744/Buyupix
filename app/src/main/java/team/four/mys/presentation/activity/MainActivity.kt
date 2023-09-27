package team.four.mys.presentation.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivityMainBinding
import team.four.mys.domain.usecases.LocaleHelperUseCase
import team.four.mys.presentation.viewmodelsactivity.MainViewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    //Categories
    var gamingPrice = 20f
    var defencePrice = 20f
    var cloudPrice = 230f
    var moviesPrice = 230f
    var booksPrice = 230f
    var musicPrice = 199f
    var otherPrice = 12.99f

    // firebase
    private lateinit var auth: FirebaseAuth

    // if code sending failed
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    // phone
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String = ""
    private lateinit var phoneNumber: String

    private companion object {
        private const val TAG = "PhoneAuthActivity"
    }

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.setTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigation()
        auth = FirebaseAuth.getInstance()
        checkUser()

        binding.bottomNavigation.setupWithNavController(navController)

        visibilityNavElements(navController)
        callbacks()
        navigationBarColor(navController)
        statusBarColor(navController)
    }

    private fun callbacks() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                navController.navigate(R.id.home_fragment)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token

                navController.navigate(R.id.code_send_fragment)
            }
        }
    }

    fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]
    }

    fun verifyPhoneNumberWithCode(code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
        signInWithPhoneAuthCredential(credential)
        // [END verify_with_code]
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    updateUI(user)
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        println(task.exception)
                        Log.e(TAG, task.exception.toString())
                    }
                }
            }
    }

    // [START resend_verification]
    fun resendVerificationCode() {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        optionsBuilder.setForceResendingToken(resendToken) // callback's ForceResendingToken
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }
    // [END resend_verification]

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            navController.navigate(R.id.home_fragment)
        }
    }

    fun setPhone(number: String) {
        phoneNumber = number
    }

    private fun checkUser() {
        if (viewModel.checkUser()) {
            navController.navigate(R.id.home_fragment)
        } else {
            navController.navigate(R.id.login_fragment)
        }
    }

    private fun navigation() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_fragment,
                R.id.statistic_fragment,
                R.id.settings_fragment -> binding.bottomNavigation.visibility = View.VISIBLE

                else -> binding.bottomNavigation.visibility = View.GONE
            }
        }
    }

    private fun statusBarColor(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_fragment -> viewModel.setStatusBarColor(
                    activity = this,
                    color = ResourcesCompat.getColor(resources, R.color.background_nav_bar, null)
                )

                else -> viewModel.setStatusBarColor(
                    activity = this,
                    color = ResourcesCompat.getColor(resources, R.color.background_main, null)
                )
            }
        }
    }

    private fun navigationBarColor(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_fragment,
                R.id.settings_fragment,
                R.id.statistic_fragment -> viewModel.setNavigationColor(
                    activity = this,
                    color = ResourcesCompat.getColor(resources, R.color.background_nav_bar, null)
                )

                else -> viewModel.setNavigationColor(
                    activity = this,
                    color = ResourcesCompat.getColor(resources, R.color.background_main, null)
                )
            }
        }
    }

    override fun attachBaseContext(base: Context) {
        LocaleHelperUseCase().setLocale(base, LocaleHelperUseCase().getLanguage(base))
        super.attachBaseContext(LocaleHelperUseCase().onAttach(base))
    }

}