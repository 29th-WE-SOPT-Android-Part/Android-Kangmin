package org.sopt.soptandroidseminar.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataEvent()
    }

    private fun getDataEvent() {
        val githubId = intent.getStringExtra("githubId")
        val githubUrl = intent.getStringExtra("githubUrl")
        val githubImage = intent.getStringExtra("githubImage")

        binding.apply {
            tvNameDetail.text = githubId
            tvRepo.text = githubUrl
            Glide.with(this@DetailActivity).load(githubImage).into(imageProfile)
        }

    }
}