package com.compose.authentication.utils

import android.util.Log
import androidx.navigation.NavController

object Constants {

    fun NavController.finishAndGoToNextScreen(nextScreen: String){
        this.popBackStack()
        this.navigate(nextScreen)
    }

    fun String.logD(msg: String) = Log.d(this, msg)
}