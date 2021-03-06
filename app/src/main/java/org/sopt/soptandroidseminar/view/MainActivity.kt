package org.sopt.soptandroidseminar.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.adapter.ViewPagerAdapter
import org.sopt.soptandroidseminar.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpMain.adapter = ViewPagerAdapter(this)
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = 2
                    return@setOnItemSelectedListener true
                }
            }
        }

    }
}