package org.sopt.soptandroidseminar.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.soptandroidseminar.adapter.FollowerViewPagerAdapter
import org.sopt.soptandroidseminar.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTabLayout()
    }

    private fun initAdapter() {
        binding.vpFollow.adapter = FollowerViewPagerAdapter(requireActivity())
    }

    private fun initTabLayout() {
        val tabLable = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tlFollow, binding.vpFollow) { tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}