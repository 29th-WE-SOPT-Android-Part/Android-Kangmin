package org.sopt.soptandroidseminar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.soptandroidseminar.view.main.profile.follow.FollowerListFragment

class FollowViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return FollowerListFragment()
    }
}