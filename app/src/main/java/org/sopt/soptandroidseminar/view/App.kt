package org.sopt.soptandroidseminar.view

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.sopt.soptandroidseminar.api.data.SOPTSharedPreferences

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SOPTSharedPreferences.init(this)
    }
}