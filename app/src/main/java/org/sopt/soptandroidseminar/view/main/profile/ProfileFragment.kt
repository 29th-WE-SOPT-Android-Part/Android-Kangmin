package org.sopt.soptandroidseminar.view.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.FragmentProfileBinding
import org.sopt.soptandroidseminar.util.BindingFragment
import org.sopt.soptandroidseminar.view.main.profile.follow.FollowerListFragment
import org.sopt.soptandroidseminar.view.main.profile.repo.RepoListFragment

@AndroidEntryPoint
class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
        getProfileImage()
        observe()
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

    private fun getProfileImage() {
        viewModel.profileImage()

    }

    private fun observe() {
        viewModel.imageUrl.observe(viewLifecycleOwner) {
            Glide.with(this).load(it).circleCrop().into(binding.ivProfile)
        }

        viewModel.followFragment.observe(viewLifecycleOwner) {
            buttonColor(true)
            fragmentConvert(FollowerListFragment())
        }
        viewModel.repoFragment.observe(viewLifecycleOwner) {
            buttonColor(false)
            fragmentConvert(RepoListFragment())

        }
    }

    private fun fragmentConvert(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_list, fragment)
            .commit()
    }

    private fun buttonColor(isFollowSelected: Boolean) {
        with(binding) {
                btnFollowerList.isSelected= isFollowSelected
                btnRepoList.isSelected= !isFollowSelected
        }
    }
}