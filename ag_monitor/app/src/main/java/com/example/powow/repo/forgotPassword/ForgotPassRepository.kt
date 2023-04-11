package com.example.powow.repo.forgotPassword

import com.example.powow.retrofit.RetrofitService

class ForgotPassRepository constructor(private val retrofitService: RetrofitService) {
    fun getForgotPassResponse(mEmail: String) = retrofitService.getForgotPassResponse(mEmail)
}