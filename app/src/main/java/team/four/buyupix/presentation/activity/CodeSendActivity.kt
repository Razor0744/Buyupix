package team.four.buyupix.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import com.google.firebase.auth.*
import team.four.buyupix.R
import team.four.buyupix.databinding.ActivityCodeSendBinding
import team.four.buyupix.domain.models.SetNavigationBarParam
import team.four.buyupix.domain.models.SetStatusBarParam
import team.four.buyupix.domain.usecases.SetNavigationBarUseCase
import team.four.buyupix.domain.usecases.SetStatusBarUseCase

class CodeSendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCodeSendBinding

    // firebase
    private lateinit var auth: FirebaseAuth
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeSendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // firebase auth
        auth = FirebaseAuth.getInstance()

        binding.code1.requestFocus()

        binding.button1.setOnClickListener {
            setPhoneNumber("1")
            focused("1")
        }
        binding.button2.setOnClickListener {
            setPhoneNumber("2")
            focused("2")
        }
        binding.button3.setOnClickListener {
            setPhoneNumber("3")
            focused("3")
        }
        binding.button4.setOnClickListener {
            setPhoneNumber("4")
            focused("4")
        }
        binding.button5.setOnClickListener {
            setPhoneNumber("5")
            focused("5")
        }
        binding.button6.setOnClickListener {
            setPhoneNumber("6")
            focused("6")
        }
        binding.button7.setOnClickListener {
            setPhoneNumber("7")
            focused("7")
        }
        binding.button8.setOnClickListener {
            setPhoneNumber("8")
            focused("8")
        }
        binding.button9.setOnClickListener {
            setPhoneNumber("9")
            focused("9")
        }
        binding.button0.setOnClickListener {
            setPhoneNumber("0")
            focused("0")
        }
        binding.button11.setOnClickListener {
            focused("11")
            if (phoneNumber.trim().isNotEmpty()) {
                phoneNumber = phoneNumber.substring(0, phoneNumber.length - 1)
                println(phoneNumber)
            }
        }

        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                this,
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationBarUseCase().execute(
            SetNavigationBarParam(
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
        inputType()
        textWatcher()
    }

    private fun inputType() {
        binding.code1.setRawInputType(InputType.TYPE_NULL)
        binding.code2.setRawInputType(InputType.TYPE_NULL)
        binding.code3.setRawInputType(InputType.TYPE_NULL)
        binding.code4.setRawInputType(InputType.TYPE_NULL)
        binding.code5.setRawInputType(InputType.TYPE_NULL)
        binding.code6.setRawInputType(InputType.TYPE_NULL)
    }

    private fun setPhoneNumber(number: String) {
        phoneNumber += number
    }

    private fun focused(number: String) {
        if (number != "11") {
            when (currentFocus) {
                binding.code1 -> binding.code1.setText(number)
                binding.code2 -> binding.code2.setText(number)
                binding.code3 -> binding.code3.setText(number)
                binding.code4 -> binding.code4.setText(number)
                binding.code5 -> binding.code5.setText(number)
                binding.code6 -> {
                    binding.code6.setText(number)
                    val storedVerificationId = intent.getStringExtra("storedVerificationId")
                    println(phoneNumber)
                    verifyPhoneNumberWithCode(storedVerificationId, phoneNumber)
                }
            }
        } else {
            when (currentFocus) {
                binding.code2 -> {
                    binding.code1.text = null
                    binding.code1.requestFocus()
                }
                binding.code3 -> {
                    binding.code2.text = null
                    binding.code2.requestFocus()
                }
                binding.code4 -> {
                    binding.code3.text = null
                    binding.code3.requestFocus()
                }
                binding.code5 -> {
                    binding.code4.text = null
                    binding.code4.requestFocus()
                }
                binding.code6 -> {
                    binding.code5.text = null
                    binding.code5.requestFocus()
                }
            }
        }
    }

    private fun textWatcher() {
        binding.code1.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code2.requestFocus()
            }
        }
        binding.code2.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code3.requestFocus()
            }
        }
        binding.code3.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code4.requestFocus()
            }
        }
        binding.code4.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code5.requestFocus()
            }
        }
        binding.code5.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code6.requestFocus()
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