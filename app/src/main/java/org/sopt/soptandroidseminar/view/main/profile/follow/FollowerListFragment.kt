package org.sopt.soptandroidseminar.view.main.profile.follow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.adapter.FollowerListAdapter
import org.sopt.soptandroidseminar.databinding.FragmentFollwerListBinding
import org.sopt.soptandroidseminar.util.MyTouchHelperCallback
import org.sopt.soptandroidseminar.util.VerticalItemDecoration
import org.sopt.soptandroidseminar.view.activity.DetailActivity

class FollowerListFragment : Fragment() {

    private val viewModel: FollowerListViewModel by viewModels()
    private var _binding: FragmentFollwerListBinding? = null
    private val binding get() = _binding!!

    private val adapter = FollowerListAdapter{
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("githubId", it.login)
            putExtra("githubUrl", it.repos_url)
            putExtra("githubImage", it.avatar_url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollwerListBinding.inflate(inflater, container, false)
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
//        val callback = MyTouchHelperCallback(adapter)
//        val touchHelper = ItemTouchHelper(callback)
//        touchHelper.attachToRecyclerView(binding.recyclerFollowerList)
        binding.recyclerFollowerList.adapter = adapter
//        adapter.starDrag(object : FollowerListAdapter.OnStartDragListener {
//            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
//                touchHelper.startDrag(viewHolder)
//            }
//        })
        followingList()
    }

    private fun followingList() {
        viewModel.followingList()
        viewModel.followList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
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
        _binding = null
    }
}