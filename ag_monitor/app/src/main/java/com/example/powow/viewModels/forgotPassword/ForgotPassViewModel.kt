package com.example.powow.viewModels.forgotPassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powow.models.login.LoginRequest
import com.example.powow.models.login.LoginResponse
import com.example.powow.repo.forgotPassword.ForgotPassRepository
import retrofit2.Call
import retrofit2.Response

class ForgotPassViewModel constructor(private val repository: ForgotPassRepository) : ViewModel() {
    val dataList = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getForgotPassResponse(mEmail: String) {
        /*val mReqModel = LoginRequest(
            mEmailOrPhone,mPassword
        )*/
        val response = repository.getForgotPassResponse(mEmail)
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
