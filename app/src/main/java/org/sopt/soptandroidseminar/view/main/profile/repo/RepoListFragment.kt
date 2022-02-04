package org.sopt.soptandroidseminar.view.main.profile.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.adapter.RepoListAdapter
import org.sopt.soptandroidseminar.databinding.FragmentRepoListBinding
import org.sopt.soptandroidseminar.util.BindingFragment
import org.sopt.soptandroidseminar.view.showToast

@AndroidEntryPoint
class RepoListFragment : BindingFragment<FragmentRepoListBinding>(R.layout.fragment_repo_list) {
    private val viewModel by viewModels<RepoListViewModel>()
    private var adapter: RepoListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserver()
    }

    private fun initAdapter() {
        adapter = RepoListAdapter()
        binding.recyclerRepoList.adapter = adapter
        repoList()
    }

    private fun repoList() {
        viewModel.repoList()
    }

    private fun initObserver() {
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }

        viewModel.serverConnect.observe(viewLifecycleOwner) {
            requireContext().showToast("서버통신 실패")
        }
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }
}