package com.example.powow.ui.activities.authentication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.powow.R
import com.example.powow.databinding.ActivityLoginBinding
import com.example.powow.repo.login.LoginRepository
import com.example.powow.retrofit.RetrofitService
import com.example.powow.ui.activities.account.CreateAccountActivity
import com.example.powow.ui.activities.dashboard.DashboardActivity
import com.example.powow.utils.Utils
import com.example.powow.viewModels.login.LoginViewModel
import com.example.powow.viewModels.login.LoginViewModelFactory


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var mEmailOrPhone = ""
    private var mPassword = ""
    var loginViewModel: LoginViewModel? = null
    private val retrofitService = RetrofitService.getInstance()
    var isCheckBox = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initClicks()
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(LoginRepository(retrofitService))
        )[LoginViewModel::class.java]
    }

    private fun initClicks() {
        binding.editEmailOrMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                changeTextMode("EmailOrPhone")
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editEmailOrMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                changeTextMode("Password")
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.layoutLoginButton.setOnClickListener {
            if (validateOnClick(it)) {
                requestLogin(it, mEmailOrPhone, mPassword)
            }
        }
        binding.textForgotPassword.setOnClickListener {
            startActivity(Intent(applicationContext, ForgotPasswordActivity::class.java))
        }
        binding.layoutGetHelp.setOnClickListener {
            startActivity(Intent(applicationContext, CreateAccountActivity::class.java)
                .putExtra("INTENT_FROM","LOGIN"))
            finish()
        }
        binding.checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            isCheckBox = isChecked
        }
    }

    private fun requestLogin(view: View, mEmailOrPhone: String, mPassword: String) {
        setLoading(true)
        observeResponse(view, mEmailOrPhone, mPassword)
    }

    private fun observeResponse(view: View, mEmailOrPhone: String, mPassword: String) {
        loginViewModel!!.dataList.observe(this) {
            Log.e("RESPONSE", "DATA : $it")
            setLoading(false)
            if (it != null) {
                Utils.showSuccessMessage(view, this, "Login successfully")
                startActivity(Intent(applicationContext, DashboardActivity::class.java))
            } else {
                Log.e("RESPONSE FALSE", it.toString())
                Utils.showToast(this, "Login failed")
            }
        }
        loginViewModel!!.errorMessage.observe(this) {
            setLoading(false)
            Log.e("ERROR RESPONSE", it.toString())
            Utils.showToast(this, "Login failed")
        }
        loginViewModel!!.getLoginResponse(mEmailOrPhone, mPassword)
    }

    private fun setLoading(status: Boolean) {
        if (status) {
            binding.layoutLoading.loadingLayout.visibility = View.VISIBLE
        } else {
            binding.layoutLoading.loadingLayout.visibility = View.GONE
        }
    }

    private fun changeTextMode(type: String) {
        if (type.equals("EmailOrPhone")) {
            binding.editEmailOrMobile.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.black
                )
            )
            binding.layoutEmail.setBackgroundResource(R.drawable.bg_edit_text)
        } else {
            binding.editPassword.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.black
                )
            )
            binding.layoutPassword.setBackgroundResource(R.drawable.bg_edit_text)
        }
    }

    private fun validateOnClick(view: View): Boolean {
        var valid = true
        mEmailOrPhone = binding.editEmailOrMobile.text.toString().trim()
        mPassword = binding.editPassword.text.toString().trim()
        if (mEmailOrPhone.isBlank()) {
            Utils.showErrorMessage(view, this, getString(R.string.enter_email_or_phone))
            valid = false
            return valid
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mEmailOrPhone)
                .matches()
        ) {
            Utils.showErrorMessage(view, this, getString(R.string.valid_email))
            valid = false
            return valid
        }
        if (mEmailOrPhone.isBlank()) {
            Utils.showErrorMessage(view, this, getString(R.string.your_passwrd))
            valid = false
            return valid
        }
        if (!isCheckBox) {
            Utils.showErrorMessage(view, this, getString(R.string.terms_and_conditions))
            valid = false
            return valid
        }
        /* if (!passwordvalidation()) {
             Utils.showErrorMessage(
                 view, this,
                 getString(R.string.passwrd_cases)
             )
             valid = false
             return valid
         }*/
        /*if (mEmailOrPhone != "abc@gmail.com" && mPassword != "abc@123") {
            Utils.showErrorMessage(view, this, getString(R.string.icorrect_email_or_passwrd))
            binding.editEmailOrMobile.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.text_error_color
                )
            )
            binding.layoutEmail.setBackgroundResource(R.drawable.bg_error_edit_text)
            binding.editPassword.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.text_error_color
                )
            )
            binding.layoutPassword.setBackgroundResource(R.drawable.bg_error_edit_text)
            valid = false
            return valid
        }*/
        return valid
    }

    fun passwordvalidation(): Boolean {
        Log.e("PASSSWORD", mPassword)
        val passwd = mPassword
        val pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$"
        return passwd.matches(pattern.toRegex())
    }
}