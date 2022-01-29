package com.example.mys

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val handler = Handler()

    private val runnable = Runnable {
        if (!isFinishing) {
            startActivity(Intent(applicationContext, FirstActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 200)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}