package com.example.powow.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.example.powow.R
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

        fun showLoader(context: Context) {
            var dialog: Dialog? = null
            if (dialog == null) {
                dialog = Dialog(context, android.R.style.Theme_NoTitleBar)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setContentView(R.layout.custom_loading_dialog)
                dialog.setCancelable(false)
                dialog.findViewById<View>(R.id.message)
            }
        }


    }

}