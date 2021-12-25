package org.sopt.soptandroidseminar.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.api.ApiServiceCreator
import org.sopt.soptandroidseminar.databinding.FragmentProfileBinding
import org.sopt.soptandroidseminar.view.activity.SettingActivity
import org.sopt.soptandroidseminar.view.enqueueUtil

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileImage()
        changeFragmentEvent()
        settingEvent()
    }

    private fun profileImage() {
        val call = ApiServiceCreator.githubApiService.getUserInfo()
        call.enqueueUtil(
            onSuccess = {
                Glide.with(this).load(it.avatar_url).circleCrop().into(binding.ivProfile)
            }
        )
    }

    private fun changeFragmentEvent() {
        binding.btnFollowerList.isSelected = true
        val followerListFragment = FollowerListFragment()
        val repoListFragment = RepoListFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_list, followerListFragment)
            .commit()

        repoListChangeEvent(repoListFragment)
        followerListChangeEvent(followerListFragment)
    }

    private fun repoListChangeEvent(repoListFragment: RepoListFragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        binding.btnRepoList.setOnClickListener {
            if (binding.btnFollowerList.isSelected && !binding.btnRepoList.isSelected) {
                binding.btnRepoList.isSelected = true
                binding.btnFollowerList.isSelected = false
                binding.btnFollowerList.setTextColor(requireActivity().resources.getColor(R.color.black))
                binding.btnRepoList.setTextColor(requireActivity().resources.getColor(R.color.white))
            }
            transaction.replace(R.id.fragment_list, repoListFragment)
            transaction.commit()
        }
    }

    private fun followerListChangeEvent(followerListFragment: FollowerListFragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        binding.btnFollowerList.setOnClickListener {
            if (!binding.btnFollowerList.isSelected && binding.btnRepoList.isSelected) {
                binding.btnRepoList.isSelected = false
                binding.btnFollowerList.isSelected = true
                binding.btnFollowerList.setTextColor(requireActivity().resources.getColor(R.color.white))
                binding.btnRepoList.setTextColor(requireActivity().resources.getColor(R.color.black))
            }
            transaction.replace(R.id.fragment_list, followerListFragment)
            transaction.commit()
        }
    }

    private fun settingEvent() {
        binding.imageSetting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}