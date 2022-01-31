package org.sopt.soptandroidseminar.view.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.soptandroidseminar.data.SoptDataStore
import org.sopt.soptandroidseminar.databinding.ActivitySettingBinding
import org.sopt.soptandroidseminar.view.App
import org.sopt.soptandroidseminar.view.showToast
import org.sopt.soptandroidseminar.view.signin.SignInActivity

class SettingActivity: AppCompatActivity() {
    private val sharedPreferences = App.sharedPreferences
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.layoutSetting.setOnClickListener {
            sharedPreferences.setLogout(this)
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            showToast("자동 로그인 해제")
            finish()
        }
    }
}