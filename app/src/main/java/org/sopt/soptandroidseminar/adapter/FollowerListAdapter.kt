package org.sopt.soptandroidseminar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.api.data.response.ResponseUserInfo
import org.sopt.soptandroidseminar.databinding.ItemFollowerListBinding
import org.sopt.soptandroidseminar.util.MyDiffUtil
import org.sopt.soptandroidseminar.util.MyTouchHelperCallback
import java.util.*

class FollowerListAdapter : RecyclerView.Adapter<FollowerListAdapter.FollowerUserViewHolder>(),
    MyTouchHelperCallback.OnItemMoveListener {

    private val userList = mutableListOf<ResponseUserInfo>()
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerListAdapter.FollowerUserViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerUserViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: FollowerListAdapter.FollowerUserViewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(userList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun afterDragAndDrop() {
        notifyDataSetChanged()
    }

    fun setItems(newItems: List<ResponseUserInfo>) {
        val diffUtil = MyDiffUtil(userList, newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        userList.clear()
        userList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: FollowerUserViewHolder, position: Int) {
        holder.onBind(userList[position], position)
    }

    inner class FollowerUserViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(followerUserInfo: ResponseUserInfo, position: Int) {
            binding.followUserName.text = followerUserInfo.login
            binding.followUserContent.text = followerUserInfo.repos_url
            Glide.with(itemView.context).load(followerUserInfo.avatar_url)
                .into(binding.followUserImage)

            itemView.setOnClickListener {
                itemClickListener.onClick(userList[position])
            }
        }
    }


    interface ItemClickListener {
        fun onClick(data: ResponseUserInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}