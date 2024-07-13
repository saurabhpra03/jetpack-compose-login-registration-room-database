package com.compose.authentication.utils

import android.content.Context
import android.widget.Toast

fun Context.showLongToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

