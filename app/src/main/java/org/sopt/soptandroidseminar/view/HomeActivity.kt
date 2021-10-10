package org.sopt.soptandroidseminar.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load("https://github.com/kkk5474096.png").into(binding.ivProfile)
        goToGithub()
    }

    private fun goToGithub() {
        binding.ivGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kkk5474096"))
            startActivity(intent)
        }
    }
}