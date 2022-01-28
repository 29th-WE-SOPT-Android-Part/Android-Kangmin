package org.sopt.soptandroidseminar.view.main.profile.detail

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
        binding.apply {
            with(intent) {
                tvNameDetail.text = getStringExtra("githubId")
                tvRepo.text = getStringExtra("githubUrl")
                Glide.with(this@DetailActivity).load(getStringExtra("githubImage")).into(imageProfile)
            }
        }
    }
}