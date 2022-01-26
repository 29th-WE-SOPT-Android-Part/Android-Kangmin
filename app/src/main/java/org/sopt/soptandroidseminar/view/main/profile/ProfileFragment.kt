package org.sopt.soptandroidseminar.view.main.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.FragmentProfileBinding
import org.sopt.soptandroidseminar.util.BindingFragment
import org.sopt.soptandroidseminar.view.activity.SettingActivity
import org.sopt.soptandroidseminar.view.main.profile.follow.FollowerListFragment
import org.sopt.soptandroidseminar.view.main.profile.repo.RepoListFragment

class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
        profileImage()
        observerFragment()
    }

    private fun initView() {
        binding.btnFollowerList.isSelected = true
        viewModel.followFragment()
    }

    private fun initEvent() {
        binding.imageSetting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }

        binding.btnRepoList.setOnClickListener {
            viewModel.repoFragment()
        }

        binding.btnFollowerList.setOnClickListener {
            viewModel.followFragment()
        }
    }

    private fun profileImage() {
        viewModel.profileImage()
        viewModel.imageUrl.observe(viewLifecycleOwner) {
            Glide.with(this).load(it).circleCrop().into(binding.ivProfile)
        }
    }

    private fun observerFragment() {

        viewModel.followFragment.observe(viewLifecycleOwner) {
            buttonColor(true)
            fragmentManager(FollowerListFragment())
        }
        viewModel.repoFragment.observe(viewLifecycleOwner) {
            buttonColor(false)
            fragmentManager(RepoListFragment())

        }
    }

    private fun fragmentManager(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_list, fragment)
            .commit()
    }

    private fun buttonColor(select: Boolean) {
        with(binding) {
                btnFollowerList.isSelected= select
                btnRepoList.isSelected= !select
            if (btnFollowerList.isSelected) {
                btnFollowerList.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                btnRepoList.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            } else {
                btnFollowerList.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                btnRepoList.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            }
        }
    }
}