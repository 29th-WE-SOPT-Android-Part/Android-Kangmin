package org.sopt.soptandroidseminar.view.main.profile.follow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.adapter.FollowerListAdapter
import org.sopt.soptandroidseminar.databinding.FragmentFollwerListBinding
import org.sopt.soptandroidseminar.util.BindingFragment
import org.sopt.soptandroidseminar.util.VerticalItemDecoration
import org.sopt.soptandroidseminar.view.activity.DetailActivity

class FollowerListFragment :
    BindingFragment<FragmentFollwerListBinding>(R.layout.fragment_follwer_list) {

    private val viewModel: FollowerListViewModel by viewModels()
    private var adapter: FollowerListAdapter? = null

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
        initAdapter()
    }

    private fun initAdapter() {
        recyclerViewDecoration()
        adapter = FollowerListAdapter {
            val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
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
        viewModel.followList.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
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
        super.onDestroyView()
        adapter = null
    }
}