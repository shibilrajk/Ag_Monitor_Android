package com.example.powow.viewModels.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.powow.repo.forgotPassword.ForgotPassRepository

class ForgotPassViewModelFactory constructor(private val repository: ForgotPassRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ForgotPassViewModel::class.java)) {
            ForgotPassViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}