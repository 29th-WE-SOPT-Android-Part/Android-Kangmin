package org.sopt.soptandroidseminar.view.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.data.SoptDataStore
import org.sopt.soptandroidseminar.databinding.ActivitySettingBinding
import org.sopt.soptandroidseminar.view.showToast
import org.sopt.soptandroidseminar.view.signin.SignInActivity
import javax.inject.Inject

@AndroidEntryPoint
class SettingActivity: AppCompatActivity() {
    @Inject
    lateinit var sharedPreferences :SoptDataStore
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.layoutSetting.setOnClickListener {
            sharedPreferences.setLogout()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            showToast("자동 로그인 해제")
            finish()
        }
    }
}