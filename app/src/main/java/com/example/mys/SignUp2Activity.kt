package com.example.mys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mys.databinding.ActivitySignUp2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp2Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUp2Binding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("Email")

        binding.continueButton.setOnClickListener {
            val signUp3Activity = Intent(this, SignUp3Activity::class.java)
            signUp3Activity.putExtra("Email", email)
            startActivity(signUp3Activity)
        }

        sendEmailVerification()
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = FirebaseAuth.getInstance().currentUser
        println(user.toString())
        user!!.sendEmailVerification()
            .addOnSuccessListener {
               println("good")
            }
        // [END send_email_verification]
    }
}