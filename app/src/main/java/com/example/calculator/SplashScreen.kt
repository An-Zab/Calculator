package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_splash)
        val handler = android.os.Handler()
        handler.postDelayed({ val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)}, 2500)

    }
}