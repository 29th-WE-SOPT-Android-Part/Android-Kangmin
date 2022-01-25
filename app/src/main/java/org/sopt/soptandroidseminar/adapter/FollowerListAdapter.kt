package org.sopt.soptandroidseminar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.DragStartHelper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.api.data.response.ResponseUser
import org.sopt.soptandroidseminar.databinding.ItemFollowerListBinding
import org.sopt.soptandroidseminar.util.MyTouchHelperCallback
import java.util.*

class FollowerListAdapter(private val listener: ItemClickListener) :
    ListAdapter<ResponseUser, FollowerListAdapter.FollowerUserViewHolder>(DIFFUTIL),
    MyTouchHelperCallback.OnItemMoveListener {
    private lateinit var dragListener: OnStartDragListener
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerListAdapter.FollowerUserViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerUserViewHolder(binding, listener)
    }


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(currentList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
//

    override fun onItemSwipe(position: Int) {
        currentList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun starDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: FollowerUserViewHolder, position: Int) {
        holder.onBind(getItem(position))
        currentList[position].let {
            with(holder) {
                itemView.setOnTouchListener { view, motionEvent ->
                    if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(this)
                    }
                    return@setOnTouchListener false
                }
            }
        }

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
        val DIFFUTIL = object : DiffUtil.ItemCallback<ResponseUser>() {
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


