package com.compose.authentication.data

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.compose.authentication.data.model.Authentication
import com.google.gson.Gson
import javax.inject.Inject

class SharedPref @Inject constructor(private val activity: Activity) {

    private val PREF_NAME = "authApp"

    private val keyAuthDetails = "authDetails"

    fun saveAuhDetails(authentication: Authentication) {

        val json = Gson().toJson(authentication)

        val editor: SharedPreferences.Editor =
            activity.getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit()
        editor.putString(keyAuthDetails, json)
        editor.apply()
    }

    fun getAuthDetails(): Authentication? {
        val pref: SharedPreferences = activity.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val json: String? = pref.getString(keyAuthDetails, null)
        return Gson().fromJson(json, Authentication::class.java)
    }

    fun logout(){
        val editor: SharedPreferences.Editor =
            activity.getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}