package com.android.elflow.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesManager(context: Context) {

    companion object {
        const val KEY_USER_EMAIL = "email"
        const val KEY_PREFERENCES = "prefs"
    }


    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)

    fun saveEmail(email:String){
        sharedPreferences.edit { putString(KEY_USER_EMAIL, email) }
    }

    fun getEmail(): String{
        return sharedPreferences.getString(KEY_USER_EMAIL, "") ?: ""
    }


}