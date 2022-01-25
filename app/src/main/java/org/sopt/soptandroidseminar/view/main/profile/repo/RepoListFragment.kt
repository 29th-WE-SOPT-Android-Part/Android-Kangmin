package org.sopt.soptandroidseminar.view.main.profile.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.soptandroidseminar.adapter.RepoListAdapter
import org.sopt.soptandroidseminar.databinding.FragmentRepoListBinding

class RepoListFragment : Fragment() {
    private val viewModel: RepoListViewModel by viewModels()
    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding!!
    private val adapter = RepoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerRepoList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerRepoList.adapter = adapter
        repoList()
    }

    private fun repoList() {
        viewModel.repoList()
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}