package org.sopt.soptandroidseminar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.data.response.ResponseUser
import org.sopt.soptandroidseminar.databinding.ItemFollowerListBinding
import java.util.*

class FollowerListAdapter(private val listener: ItemClickListener) :
    ListAdapter<ResponseUser, FollowerListAdapter.FollowerUserViewHolder>(DIFFUTIL) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerListAdapter.FollowerUserViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerUserViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FollowerUserViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun interface ItemClickListener {
        fun onClick(data: ResponseUser)
    }

    class FollowerUserViewHolder(
        private val binding: ItemFollowerListBinding,
        private val listener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(followerUser: ResponseUser) {
            binding.user = followerUser

            binding.root.setOnClickListener {
                listener.onClick(followerUser)
            }
        }
    }

    companion object {
        private val DIFFUTIL = object : DiffUtil.ItemCallback<ResponseUser>() {
            override fun areItemsTheSame(
                oldItem: ResponseUser,
                newItem: ResponseUser
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseUser,
                newItem: ResponseUser
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}


