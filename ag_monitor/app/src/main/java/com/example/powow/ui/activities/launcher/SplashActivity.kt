package com.example.powow.ui.activities.launcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.powow.databinding.ActivityForgotPasswordBinding
import com.example.powow.databinding.ActivitySplashBinding
import com.example.powow.helpers.UserSessionManager
import com.example.powow.ui.activities.authentication.LoginActivity
import com.example.powow.ui.activities.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSplash()
    }

    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val userSessionManager = UserSessionManager(this@SplashActivity)
            if (userSessionManager.getIsUserLoggedIn()) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 3000)
    }
}