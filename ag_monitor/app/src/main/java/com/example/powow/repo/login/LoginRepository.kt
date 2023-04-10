package com.example.powow.repo.login

import com.example.powow.models.login.LoginRequest
import com.example.powow.retrofit.RetrofitService

class LoginRepository constructor(private val retrofitService: RetrofitService) {
    fun getLoginResponse(mReqModel : LoginRequest) =
        retrofitService.getLoginResponse(mReqModel)
}