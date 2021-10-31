package org.sopt.soptandroidseminar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.soptandroidseminar.view.CameraFragment
import org.sopt.soptandroidseminar.view.HomeFragment
import org.sopt.soptandroidseminar.view.ProfileFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> HomeFragment()
            else -> CameraFragment()
        }
    }
}