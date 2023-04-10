package com.example.powow.helpers

import android.content.Context
import android.content.SharedPreferences

class UserSessionManager(context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserSharedPref", Context.MODE_PRIVATE)

    private val isUserLogedin = "isUserLoggedIn"
    private val userId = "userId"

    fun setIsUserLoggedIn(value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(isUserLogedin, value)
        editor.apply()
    }

    fun getIsUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(isUserLogedin, true)
    }

    fun setUserId(mUserId: String) {
        sharedPreferences.edit().putString(userId, mUserId).apply()
    }

    fun getUserId(): String {
        return sharedPreferences.getString(userId, "")!!
    }

}