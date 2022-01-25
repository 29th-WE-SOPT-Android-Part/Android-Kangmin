package org.sopt.soptandroidseminar.view.main.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.databinding.FragmentProfileBinding
import org.sopt.soptandroidseminar.view.activity.SettingActivity
import org.sopt.soptandroidseminar.view.main.profile.follow.FollowerListFragment
import org.sopt.soptandroidseminar.view.main.profile.repo.RepoListFragment

class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        profileImage()
        changeFragment()

    }

    private fun profileImage() {
        viewModel.profileImage()
        viewModel.imageUrl.observe(viewLifecycleOwner) {
            Glide.with(this).load(it).circleCrop().into(binding.ivProfile)
        }
    }

    private fun changeFragment() {
        binding.btnFollowerList.isSelected = true
        val followerListFragment = FollowerListFragment()
        val repoListFragment = RepoListFragment()
        viewModel.setFragment()

        viewModel.fragment.observe(viewLifecycleOwner) {
            if (it) {
                followerListColor()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_list, followerListFragment)
                    .commit()
            } else {
                repoListColor()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_list, repoListFragment)
                    .commit()
            }
        }
    }

    private fun repoListColor() {
        with(binding) {
            btnRepoList.isSelected = true
            btnFollowerList.isSelected = false
            btnFollowerList.setTextColor(requireActivity().resources.getColor(R.color.black))
            btnRepoList.setTextColor(requireActivity().resources.getColor(R.color.white))
        }
    }

    private fun followerListColor() {
        with(binding) {
            btnRepoList.isSelected = false
            btnFollowerList.isSelected = true
            btnFollowerList.setTextColor(requireActivity().resources.getColor(R.color.white))
            btnRepoList.setTextColor(requireActivity().resources.getColor(R.color.black))
        }
    }

    private fun initView() {
        binding.imageSetting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }

        binding.btnRepoList.setOnClickListener {
            viewModel.setFalseFragment()
        }

        binding.btnFollowerList.setOnClickListener {
            viewModel.setFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}