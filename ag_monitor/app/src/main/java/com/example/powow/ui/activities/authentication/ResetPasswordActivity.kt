package com.example.powow.ui.activities.authentication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.powow.R
import com.example.powow.databinding.ActivityResetPasswordBinding
import com.example.powow.utils.Utils

class ResetPasswordActivity : AppCompatActivity() {
    private var mPassword = ""
    private var mConfirmPassword = ""
    private lateinit var binding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClick()
    }

    private fun initClick() {
        binding.buttonReset.setOnClickListener {
            if (validateOnClick(it)) {
                Utils.showToast(this, "Your password has been reset successfully")
                Utils.showSuccessMessage(it, this, "Your password has been reset successfully")
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        }
        binding.layoutGetHelp.setOnClickListener {
            if (validateOnClick(it)) {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        }
    }

    private fun validateOnClick(view: View): Boolean {
        var valid = true
        mPassword = binding.editNewPassword.text.toString().trim()
        mConfirmPassword = binding.editConfirmPassword.text.toString().trim()
        if (mPassword.isBlank()) {
            Utils.showErrorMessage(
                view, this,
                getString(R.string.new_passwrd)
            )
            valid = false
            return valid
        }
        if (mConfirmPassword.isBlank()) {
            Utils.showErrorMessage(
                view, this,
                getString(R.string.enter_confirm_passwrd)
            )
            valid = false
            return valid
        }
        if (!passwordvalidation()) {
            Utils.showErrorMessage(
                view, this,
                getString(R.string.passwrd_cases)
            )
            valid = false
            return valid
        }
        if (mPassword != mConfirmPassword) {
            Utils.showErrorMessage(
                view, this,
                getString(R.string.passwrd_not_match)
            )
            valid = false
            return valid
        }
        return valid
    }

    fun passwordvalidation(): Boolean {
        Log.e("PASSSWORD", mPassword)
        val passwd = mPassword
        val pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$"
        return passwd.matches(pattern.toRegex())
    }
}