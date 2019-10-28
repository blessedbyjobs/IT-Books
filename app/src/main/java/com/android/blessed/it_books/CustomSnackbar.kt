package com.android.blessed.it_books

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class CustomSnackBar(private val my_snackBar: Snackbar, private val context: Context) {
    val snackbar: Snackbar
        get() {
            val snackBarView = my_snackBar.view
            snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.errorColor))
            val snackBarText = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            snackBarText.setTextColor(context.resources.getColor(R.color.inactiveColor))
            return my_snackBar
        }
}