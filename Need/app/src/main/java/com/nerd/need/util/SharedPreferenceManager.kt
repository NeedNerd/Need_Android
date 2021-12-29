package com.nerd.need.util

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences

object SharedPreferenceManager {

    private const val PREF_TOKEN = "token"

    fun setToken(context: Context, token: String) {
        getDefaultSharedPreferences(context).edit().putString(PREF_TOKEN, token).apply()
    }

    fun getToken(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_TOKEN, null)
    }
}