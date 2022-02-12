package com.example.mys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mys.databinding.ActivitySignUp1Binding

class SignUp1Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUp1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCodeButton.setOnClickListener {
            val signUp2Activity = Intent(this, SignUp2Activity::class.java)
            val email = binding.login.text.toString()
            signUp2Activity.putExtra("Email", email)
            startActivity(signUp2Activity)
        }
    }
}