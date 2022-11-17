package team.four.buyupix.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import team.four.buyupix.databinding.ActivityLoginBinding
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // firebase
    private lateinit var auth: FirebaseAuth

    // if code sending failed
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    // phone
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String? = ""

    private companion object {
        private const val TAG = "PhoneAuthActivity"
    }

    val countryCodes = arrayListOf("+7", "+11", "+375")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // firebase auth
        auth = FirebaseAuth.getInstance()

        // Configure Phone Sign In
//        binding.continueButton.setOnClickListener {
//            val phoneNumber = binding.phoneNumber.text.toString().trim()
//            if (phoneNumber.isNotEmpty()) {
//                startPhoneNumberVerification(phoneNumber)
//            } else {
//                Toast.makeText(this, "Please enter your phone", Toast.LENGTH_LONG)
//                    .show()
//            }
//        }

        binding.countryCode.setOnClickListener {
            startActivity(Intent(this, CountryCodeActivity::class.java))
        }

        binding.button1.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 1
            binding.phoneNumber.setText(text)
        }
        binding.button2.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 2
            binding.phoneNumber.setText(text)
        }
        binding.button3.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 3
            binding.phoneNumber.setText(text)
        }
        binding.button4.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 4
            binding.phoneNumber.setText(text)
        }
        binding.button5.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 5
            binding.phoneNumber.setText(text)
        }
        binding.button6.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 6
            binding.phoneNumber.setText(text)
        }
        binding.button7.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 7
            binding.phoneNumber.setText(text)
        }
        binding.button8.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 8
            binding.phoneNumber.setText(text)
        }
        binding.button9.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 9
            binding.phoneNumber.setText(text)
        }
        binding.button0.setOnClickListener {
            val text = binding.phoneNumber.text.toString() + 0
            binding.phoneNumber.setText(text)
        }
        binding.button11.setOnClickListener {
            if (binding.phoneNumber.text.toString().trim().length > 1) {
                val text = binding.phoneNumber.text.toString().trim()
                    .substring(0, binding.phoneNumber.text.toString().trim().length - 1)
                binding.phoneNumber.setText(text)
            }
        }

        keyboardFalse()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                println(e)
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

                val intent = Intent(applicationContext, CodeSendActivity::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                startActivity(intent)
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun keyboardFalse() {
        binding.phoneNumber.setRawInputType(InputType.TYPE_NULL)
        binding.phoneNumber.requestFocus()
        textInputType()
        val number = intent.getStringExtra("number")
        if (number != null) {
            binding.phoneNumber.setText("$number")
        }
    }

    private fun textInputType() {
        binding.phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var i = 0
                while (i <= 2) {
                    if (s.toString() == countryCodes[i]) {
                        var str = s.toString()
                        when (s?.length) {
                            4 -> str += "     "
                            3 -> str += "       "
                            2 -> str += "         "
                        }
                        binding.phoneNumber.setText(str)
                        binding.phoneNumber.setSelection(str.length)
                    } else if (s?.length == 5) {
                        var str = s.toString()
                        str += "   "
                        binding.phoneNumber.setText(str)
                        binding.phoneNumber.setSelection(str.length)
                    }
                    i++
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]
    }
}
