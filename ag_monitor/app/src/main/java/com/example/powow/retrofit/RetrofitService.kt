package com.example.powow.retrofit

import com.example.powow.models.login.LoginRequest
import com.example.powow.models.login.LoginResponse
import com.example.powow.utils.URLs
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @POST(URLs.LOGIN)
    fun getLoginResponse(@Body request: LoginRequest?): Call<LoginResponse?>

    companion object {
        private val stethoClient: OkHttpClient
            get() = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        private fun okClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .readTimeout(10, TimeUnit.MINUTES)
                .build()
        }

        fun getInstance(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(stethoClient)
                .client(okClient())
                .build()
                .create(RetrofitService::class.java)
        }
    }

}