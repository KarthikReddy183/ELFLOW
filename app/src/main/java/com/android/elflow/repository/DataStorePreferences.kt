package com.android.elflow.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
* Check how to save images with shared pref, data store &  room db*/


const val USER_EMAIL_PREFERENCES_NAME = "email_preferences"
//why it did not work without private val ?? Even usage of comment besides the constructor syntax is causing an issue ??


class DataStorePreferences(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_EMAIL_PREFERENCES_NAME)
        val EMAIL_KEY = stringPreferencesKey("user_email")
    }


    //this is a suspend fun, so while calling call it inside a co-routine scope
    suspend fun saveToDataStore(value: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = value
        }
    }

    fun getData(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }
    }

}