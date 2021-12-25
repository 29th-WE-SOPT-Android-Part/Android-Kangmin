package org.sopt.soptandroidseminar.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.soptandroidseminar.databinding.ActivitySettingBinding
import org.sopt.soptandroidseminar.util.SOPTSharedPreferences
import org.sopt.soptandroidseminar.view.showToast

class SettingActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        autoLoginEvent()
    }

    private fun autoLoginEvent() {
        binding.layoutSetting.setOnClickListener {
            SOPTSharedPreferences.setLogout(this)
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            showToast("자동 로그인 해제")
            finish()
        }
    }
}