package com.weatherapp.demo.utilities

import android.text.TextUtils
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

object MessageUtility {

    fun showMessage(message: String?, view: ViewGroup) {
        if (!TextUtils.isEmpty(message)) {
            showMessageUsingSnackbar(message, view)
        }
    }

    fun showMessageUsingSnackbar(message: String?, view: ViewGroup) {
        val snackbar = Snackbar.make(view, message ?: "Error", Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}