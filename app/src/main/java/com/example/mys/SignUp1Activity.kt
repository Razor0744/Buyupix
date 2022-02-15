package com.example.mys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mys.databinding.ActivitySignUp1Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp1Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUp1Binding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCodeButton.setOnClickListener {
            val email = binding.login.text.toString()
            createAccount(email, "Razor3075217")
        }
    }

    private fun createAccount(email: String, password: String) {
        auth = Firebase.auth
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Неверный Email",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val signUp2Activity = Intent(this, SignUp2Activity::class.java)
            val email = binding.login.text.toString()
            signUp2Activity.putExtra("Email", email)
           startActivity(signUp2Activity)
        }
    }
}