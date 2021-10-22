package org.sopt.soptandroidseminar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.soptandroidseminar.adapter.FollowerListAdapter
import org.sopt.soptandroidseminar.adapter.RepoListAdapter
import org.sopt.soptandroidseminar.api.GithubServiceCreator
import org.sopt.soptandroidseminar.databinding.FragmentFollwerListBinding
import org.sopt.soptandroidseminar.databinding.FragmentRepoListBinding
import org.sopt.soptandroidseminar.util.MyTouchHelperCallback

class RepoListFragment : Fragment() {

    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding!!
    private val adapter = RepoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myFollowList()
    }

    private fun myFollowList() {
        binding.recyclerRepoList.layoutManager = GridLayoutManager(requireContext(), 2)
        val callback = MyTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.recyclerRepoList)
        binding.recyclerRepoList.adapter = adapter
        adapter.startDrag(object : FollowerListAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerListAdapter.FollowerUserViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
        userFollowingList()
    }

    private fun userFollowingList() {
        val call = GithubServiceCreator.apiService.reposForUser()
        call.enqueueUtil(
            onSuccess = {
                adapter.setItems(it)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}