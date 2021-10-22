package org.sopt.soptandroidseminar.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.adapter.FollowerListAdapter

class MyTouchHelperCallback(
    private val itemMoveListener: OnItemMoveListener
) : ItemTouchHelper.Callback() {

    private var isMoved = false

    interface OnItemMoveListener {
        fun onItemMove(fromPosition: Int, toPosition: Int)
        fun startDrag(onStartDragListener: FollowerListAdapter.OnStartDragListener) {}
        fun onItemSwipe(position: Int)
    }

    /**
     * 어느 방향으로 움직일지에 따라서 flag 받는것을 정의
     * 드래그는 위, 아래 액션이기 때문에 up, down 을 넘겨줌
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags =
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, swipeFlags)
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

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (isMoved) {
            isMoved = false
            val adapter = FollowerListAdapter()
            adapter.afterDragAndDrop()
        }
    }

    /**
     * 좌우 스와이프
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemMoveListener.onItemSwipe(viewHolder.getAdapterPosition());
    }
}