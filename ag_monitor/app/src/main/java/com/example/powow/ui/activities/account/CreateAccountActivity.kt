package com.example.powow.ui.activities.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.powow.R
import com.example.powow.databinding.ActivityCreateAccountBinding
import com.example.powow.ui.activities.authentication.LoginActivity
import com.example.powow.utils.Utils

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private var mFirstName = ""
    private var mLastName = ""
    private var mCompanyName = ""
    private var mEmail = ""
    private var intentFrom = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        initClicks()
    }

    private fun getIntentData() {
        intentFrom = intent.getStringExtra("INTENT_FROM").toString()
    }

    private fun initClicks() {
        binding.textSignIn.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
        binding.layoutRegisterButton.setOnClickListener {
            if (validateOnClick(it)) {
                Utils.showSuccessMessage(it, this, "Successfully created the account.")
                Utils.showToast(this, "Successfully created the account.")
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        }
        binding.layoutAlreadyAccount.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }
    private fun validateOnClick(view: View): Boolean {
        var valid = true
        mFirstName = binding.editFirstName.text.toString().trim()
        mLastName = binding.editLastName.text.toString().trim()
        mCompanyName = binding.editCompanyName.text.toString().trim()
        mEmail = binding.editEmail.text.toString().trim()
        if (mFirstName.isBlank()) {
            Utils.showErrorMessage(view, this, "Please enter your first name")
            valid = false
            return valid
        }
        if (mLastName.isBlank()) {
            Utils.showErrorMessage(view, this, "Please enter your last name")
            valid = false
            return valid
        }
        if (mCompanyName.isBlank()) {
            Utils.showErrorMessage(view, this, "Please enter your company name")
            valid = false
            return valid
        }
        if (mEmail.isBlank()) {
            Utils.showErrorMessage(view, this, "Please enter your email")
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

    override fun onBackPressed() {
        if (intentFrom == "LOGIN") {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }else {
            super.onBackPressed()
        }
    }
}