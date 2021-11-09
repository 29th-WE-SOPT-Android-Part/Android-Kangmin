package org.sopt.soptandroidseminar.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.soptandroidseminar.adapter.FollowerListAdapter
import org.sopt.soptandroidseminar.api.GithubServiceCreator
import org.sopt.soptandroidseminar.api.data.response.ResponseUserInfo
import org.sopt.soptandroidseminar.databinding.FragmentFollwerListBinding
import org.sopt.soptandroidseminar.util.MyTouchHelperCallback
import org.sopt.soptandroidseminar.util.VerticalItemDecoration

class FollowerListFragment : Fragment() {

    private var _binding: FragmentFollwerListBinding? = null
    private val binding get() = _binding!!

    private val adapter = FollowerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollwerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myFollowList()
        configureClickEvent()
    }

    private fun myFollowList() {
        recyclerViewDecoration()
        binding.recyclerFollowerList.layoutManager = LinearLayoutManager(requireContext())
        val callback = MyTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.recyclerFollowerList)
        binding.recyclerFollowerList.adapter = adapter
        adapter.startDrag(object : FollowerListAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerListAdapter.FollowerUserViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
        userFollowingList()
    }

    private fun userFollowingList() {
        val call = GithubServiceCreator.apiService.getFollowingInfo()

        call.enqueueUtil(
            onSuccess = {
                adapter.setItems(it)
            }
        )
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

    private fun configureClickEvent() {
        adapter.setItemClickListener(object : FollowerListAdapter.ItemClickListener {
            override fun onClick(data: ResponseUserInfo) {
                var githubId = data.login
                var githubUrl = data.repos_url
                var githubImage = data.avatar_url
                showDetailActivity(githubId, githubUrl, githubImage)
            }
        })
    }

    private fun showDetailActivity(githubId: String, githubUrl: String, githubImage: String) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("githubId", githubId)
            putExtra("githubUrl", githubUrl)
            putExtra("githubImage", githubImage)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}