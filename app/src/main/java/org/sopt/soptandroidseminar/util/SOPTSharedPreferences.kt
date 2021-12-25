package org.sopt.soptandroidseminar.util

import android.content.Context

object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    fun getAutoLogin(context: Context) : Boolean {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        return preferences.getBoolean(AUTO_LOGIN, false)
    }
    fun setAutoLogin(context: Context, value: Boolean) {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context) {
        val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }
}