package org.sopt.soptandroidseminar.view.main.profile.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.adapter.RepoListAdapter
import org.sopt.soptandroidseminar.databinding.FragmentRepoListBinding
import org.sopt.soptandroidseminar.util.BindingFragment

@AndroidEntryPoint
class RepoListFragment : BindingFragment<FragmentRepoListBinding>(R.layout.fragment_repo_list) {
    private val viewModel by viewModels<RepoListViewModel>()
    private var adapter: RepoListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = RepoListAdapter()
        binding.recyclerRepoList.adapter = adapter
        repoList()
    }

    private fun repoList() {
        viewModel.repoList()
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }
}