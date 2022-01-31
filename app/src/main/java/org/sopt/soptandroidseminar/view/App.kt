package org.sopt.soptandroidseminar.view

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import org.sopt.soptandroidseminar.data.SoptDataStore

@HiltAndroidApp
class App: Application() {

    companion object {
        lateinit var sharedPreferences: SoptDataStore
    }


    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SoptDataStore(this)
    }
}