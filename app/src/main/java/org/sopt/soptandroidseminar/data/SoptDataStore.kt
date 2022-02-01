package org.sopt.soptandroidseminar.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val STORAGE_KEY = "USER_AUTH"
private const val AUTO_LOGIN = "AUTO_LOGIN"

// This is singleton
class SoptDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)

    fun setName(value: String) {
        sharedPreferences.edit()
            .putString("name", value)
            .apply()
    }

    fun getName(): String {
        return sharedPreferences.getString("name", "").toString()
    }

    fun getAutoLogin(): Boolean {
        return sharedPreferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout() {
        sharedPreferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }
}