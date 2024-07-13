package com.compose.authentication.utils

import android.content.Context
import android.util.Patterns
import com.compose.authentication.R

object Validator {

    fun validateName(context: Context, name: String): String {
        return when {
            name.isEmpty() -> context.getString(R.string.error_name)
            else -> ""
        }
    }

    fun validateEmailId(context: Context, emailId: String): String {
        return when {
            emailId.isEmpty() -> context.getString(R.string.error_email_id)
            !Patterns.EMAIL_ADDRESS.matcher(emailId).matches() -> context.getString(R.string.invalid_email_id)
            else -> ""
        }
    }

    fun validatePassword(context: Context, password: String): String {
        return when {
            password.isEmpty() -> context.getString(R.string.error_password)
            password.length < 5 -> context.getString(R.string.invalid_password_length)
            else -> ""
        }
    }
}