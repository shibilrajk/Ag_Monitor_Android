package com.example.powow.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.example.powow.R
import com.example.powow.ui.activities.authentication.ResetPasswordActivity
import com.google.android.material.snackbar.Snackbar


class Utils {
    companion object {
        fun showErrorMessage(view: View, context: Activity, message: String) {
            val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            params.topMargin = 65
            snackbar.view.layoutParams = params
            val customSnackView: View = LayoutInflater.from(view.context)
                .inflate(R.layout.layout_snakbar_error, null)
            val customText = customSnackView.findViewById<TextView>(R.id.textMessage)
            customText?.text = message
            snackbar.view.setBackgroundColor(Color.TRANSPARENT)
            val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
            snackbar.animationMode = Snackbar.ANIMATION_MODE_FADE
            snackbarLayout.addView(customSnackView, 0)
            snackbar.show()
        }

        @SuppressLint("MissingInflatedId")
        fun showSuccessMessage(view: View, context: Activity, message: String) {
            val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            params.topMargin = 65
            snackbar.view.layoutParams = params
            val customSnackView: View = LayoutInflater.from(view.context)
                .inflate(R.layout.layout_snakbar_success, null)
            val customTextSuccess = customSnackView.findViewById<TextView>(R.id.textSuccess)
            customTextSuccess?.text = message
            snackbar.view.setBackgroundColor(Color.TRANSPARENT)
            val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
            snackbar.animationMode = Snackbar.ANIMATION_MODE_FADE
            snackbarLayout.addView(customSnackView, 0)
            snackbar.show()
        }

        fun showToast(context: Activity, message: String) {
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }
    }

}