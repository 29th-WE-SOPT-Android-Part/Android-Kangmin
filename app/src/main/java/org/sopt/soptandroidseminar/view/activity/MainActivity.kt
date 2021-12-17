package org.sopt.soptandroidseminar.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.adapter.ViewPagerAdapter
import org.sopt.soptandroidseminar.databinding.ActivityMainBinding

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
        binding.bnvMain.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

    }
}