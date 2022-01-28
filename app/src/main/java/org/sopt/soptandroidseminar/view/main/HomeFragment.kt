package org.sopt.soptandroidseminar.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.adapter.FollowViewPagerAdapter
import org.sopt.soptandroidseminar.databinding.FragmentHomeBinding
import org.sopt.soptandroidseminar.util.BindingFragment


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTabLayout()
    }

    private fun initAdapter() {
        binding.vpFollow.adapter = FollowViewPagerAdapter(requireActivity())
    }

    private fun initTabLayout() {
        val tabLable = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tlFollow, binding.vpFollow) { tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }
}