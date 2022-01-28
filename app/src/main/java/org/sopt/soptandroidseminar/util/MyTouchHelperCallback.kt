package org.sopt.soptandroidseminar.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.adapter.FollowerListAdapter

class MyTouchHelperCallback(
    private val itemMoveListener: OnItemMoveListener
) : ItemTouchHelper.Callback() {

    private var isMoved = false

    interface OnItemMoveListener {
        fun onItemMove(fromPosition: Int, toPosition: Int)
        fun onItemSwipe(position: Int)
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        } else {
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            makeMovementFlags(dragFlags, swipeFlags)
        }

    }

    /**
     * 어느 위치에서 어느 위치로 변경하는지
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        itemMoveListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemMoveListener.onItemSwipe(viewHolder.getAdapterPosition());
    }

    override fun isLongPressDragEnabled(): Boolean = false
}