package org.sopt.soptandroidseminar.view.main.profile.follow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.adapter.FollowerListAdapter
import org.sopt.soptandroidseminar.databinding.FragmentFollwerListBinding
import org.sopt.soptandroidseminar.util.BindingFragment
import org.sopt.soptandroidseminar.util.VerticalItemDecoration
import org.sopt.soptandroidseminar.view.main.profile.detail.DetailActivity
import org.sopt.soptandroidseminar.view.showToast

@AndroidEntryPoint
class FollowerListFragment :
    BindingFragment<FragmentFollwerListBinding>(R.layout.fragment_follwer_list) {

    private val viewModel by viewModels<FollowerListViewModel>()
    private var adapter: FollowerListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserver()
    }

    private fun initAdapter() {
        recyclerViewDecoration()
        adapter = FollowerListAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("githubId", it.login)
                putExtra("githubUrl", it.repos_url)
                putExtra("githubImage", it.avatar_url)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
        binding.recyclerFollowerList.adapter = adapter
        followingList()
    }

    private fun followingList() {
        viewModel.followingList()
    }

    private fun initObserver() {
        viewModel.followList.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
        viewModel.serverConnect.observe(viewLifecycleOwner) {
            requireContext().showToast("서버연결 실패")
        }
    }

    private fun recyclerViewDecoration() {
        val spaceDecoration = VerticalItemDecoration(10)
        val dividerItemDecoration =
            DividerItemDecoration(
                binding.recyclerFollowerList.context,
                LinearLayoutManager(requireContext()).orientation
            )
        binding.recyclerFollowerList.addItemDecoration(dividerItemDecoration)
        binding.recyclerFollowerList.addItemDecoration(spaceDecoration)
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()

    }
}