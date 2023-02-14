package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivityAuthenticationBinding
import team.four.mys.presentation.fragments.CodeSendFragment
import team.four.mys.presentation.fragments.LoginFragment
import team.four.mys.presentation.viewmodelsactivity.AuthenticationViewModel
import java.util.concurrent.TimeUnit

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    //Fragments
    private val loginFragment = LoginFragment()
    private val codeSendFragment = CodeSendFragment()

    private val viewModel by viewModel<AuthenticationViewModel>()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //MainFragment
        replaceFragment(loginFragment, key = null, value = null)

        // firebase auth
        auth = FirebaseAuth.getInstance()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                println("completed")
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                println(e)
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

                replaceFragment(codeSendFragment, key = null, value = null)
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
                    println("signInWithCredential:success")

                    val user = task.result?.user
                    updateUI(user)
                } else {
                    // Sign in failed, display a message and update the UI
                    println("signInWithCredential:failure")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        println(task.exception)
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
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun replaceFragment(fragment: Fragment, key: String?, value: String?) {
        val bundle = Bundle()
        bundle.putString(key, value)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    fun setPhone(phone: String) {
        phoneNumber = phone
    }
}
