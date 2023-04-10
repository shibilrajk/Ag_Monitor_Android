package com.example.powow.viewModels.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powow.models.login.LoginRequest
import com.example.powow.models.login.LoginResponse
import com.example.powow.repo.login.LoginRepository
import retrofit2.Call
import retrofit2.Response

class LoginViewModel constructor(private val repository: LoginRepository) : ViewModel() {
    val dataList = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getLoginResponse(mEmailOrPhone: String, mPassword: String) {
        val mReqModel = LoginRequest(
            mEmailOrPhone,mPassword
        )
        val response = repository.getLoginResponse(mReqModel)
        response.enqueue(object : retrofit2.Callback<LoginResponse?> {

            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                dataList.postValue(response.body())
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
