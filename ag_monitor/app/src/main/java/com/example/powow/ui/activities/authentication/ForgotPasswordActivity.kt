package com.example.powow.ui.activities.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.powow.R
import com.example.powow.databinding.ActivityForgotPasswordBinding
import com.example.powow.helpers.UserSessionManager
import com.example.powow.repo.forgotPassword.ForgotPassRepository
import com.example.powow.repo.login.LoginRepository
import com.example.powow.retrofit.RetrofitService
import com.example.powow.ui.activities.account.CreateAccountActivity
import com.example.powow.ui.activities.dashboard.DashboardActivity
import com.example.powow.utils.Utils
import com.example.powow.viewModels.forgotPassword.ForgotPassViewModel
import com.example.powow.viewModels.forgotPassword.ForgotPassViewModelFactory
import com.example.powow.viewModels.login.LoginViewModel
import com.example.powow.viewModels.login.LoginViewModelFactory

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private var mEmail = ""
    var forgotPassViewModel: ForgotPassViewModel? = null
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initClicks()
    }

    private fun initViewModel() {
        forgotPassViewModel = ViewModelProvider(
            this,
            ForgotPassViewModelFactory(ForgotPassRepository(retrofitService))
        )[ForgotPassViewModel::class.java]
    }

    private fun initClicks() {
        binding.layoutSendButton.setOnClickListener {
            if (validateOnClick(it)) {
//                requestSendMail(it, mEmail)
                Utils.showSuccessMessage(it, this, "A reset password link has been sent to your registered email address")
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
            startActivity(
                Intent(applicationContext, CreateAccountActivity::class.java)
                    .putExtra("INTENT_FROM", "FORGOT_PASSWORD")
            )
        }
    }

    private fun requestSendMail(view: View?, mEmail: String) {
        setLoading(true)
        observeResponse(view, mEmail)
    }

    private fun observeResponse(view: View?, mEmail: String) {
        forgotPassViewModel!!.dataList.observe(this) {
            Log.e("RESPONSE", "DATA : $it")
            setLoading(false)
            if (it != null) {
                Utils.showSuccessMessage(view!!, this, "A reset password link has been sent to your registered email address")
                binding.editEmail.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.edit_text_stroke_color
                    )
                )
                binding.layoutEmail.setBackgroundResource(R.drawable.bg_edit_text)
                binding.layoutSendButton.setBackgroundResource(R.drawable.bg_button_after_click)
                binding.layoutSendButton.isClickable = false
            } else {
                Log.e("RESPONSE FALSE", it.toString())
                Utils.showToast(this, "Login failed")
            }
        }
        forgotPassViewModel!!.errorMessage.observe(this) {
            setLoading(false)
            Log.e("ERROR RESPONSE", it.toString())
            Utils.showToast(this, "Login failed")
        }
        forgotPassViewModel!!.getForgotPassResponse(mEmail)
    }

    private fun setLoading(status: Boolean) {
        if (status) {
            binding.layoutLoading.loadingLayout.visibility = View.VISIBLE
        } else {
            binding.layoutLoading.loadingLayout.visibility = View.GONE
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