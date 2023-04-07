package com.example.powow.ui.activities.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import com.example.powow.R
import com.example.powow.databinding.ActivityForgotPasswordBinding
import com.example.powow.ui.activities.account.CreateAccountActivity
import com.example.powow.utils.Utils

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private var mEmail = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()
    }
    private fun initClicks() {
        binding.layoutSendButton.setOnClickListener {
            if (validateOnClick(it)) {
                Utils.showSuccessMessage(it, this, "A reset password link has been sent to your registered email address")
//                startActivity(Intent(applicationContext, ResetPasswordActivity::class.java))
                binding.editEmail.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.edit_text_stroke_color
                    )
                )
                binding.layoutEmail.setBackgroundResource(R.drawable.bg_edit_text)
                binding.layoutSendButton.setBackgroundResource(R.drawable.bg_button_after_click)
                binding.layoutSendButton.isClickable = false
            }
        }

        binding.layoutGetHelp.setOnClickListener {
            startActivity(Intent(applicationContext, CreateAccountActivity::class.java))
        }
    }
    private fun validateOnClick(view: View): Boolean {
        var valid = true
        mEmail = binding.editEmail.text.toString().trim()
        if (mEmail.isBlank()) {
            Utils.showErrorMessage(view, this, getString(R.string.please_enter_email))
            valid = false
            return valid
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail)
                .matches()
        ) {
            Utils.showErrorMessage(view, this, getString(R.string.valid_email))
            valid = false
            return valid
        }
        return valid
    }
}