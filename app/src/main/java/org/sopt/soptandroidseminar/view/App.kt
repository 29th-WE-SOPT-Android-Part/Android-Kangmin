package org.sopt.soptandroidseminar.view

import android.app.Application
import org.sopt.soptandroidseminar.api.data.SOPTSharedPreferences

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SOPTSharedPreferences.init(this)
    }
}