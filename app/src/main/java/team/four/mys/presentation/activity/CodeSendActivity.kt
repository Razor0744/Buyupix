package team.four.mys.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.*
import team.four.mys.databinding.ActivityCodeSendBinding

class CodeSendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCodeSendBinding

    // firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeSendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // firebase auth
        auth = FirebaseAuth.getInstance()

        val storedVerificationId = intent.getStringExtra("storedVerificationId")

        binding.continueButton.setOnClickListener {
            val code = binding.code1.text.toString().trim()
            if (code.isNotEmpty()) {
                verifyPhoneNumberWithCode(storedVerificationId, code)
            }
        }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
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

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            finish()
        }
    }
}