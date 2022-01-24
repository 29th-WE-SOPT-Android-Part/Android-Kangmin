package org.sopt.soptandroidseminar.api.data

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }

    fun getAutoLogin() : Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }
    fun setAutoLogin(value: Boolean) {
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
    fun setName(value: String) {
        preferences.edit()
            .putString("name", value)
            .apply()
    }

    fun getName(): String{
        return preferences.getString("name","").toString()
    }

}