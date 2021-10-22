package org.sopt.soptandroidseminar.util

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil<RepoInfo>(
    private val oldItems: List<RepoInfo>,
    private val newItems: List<RepoInfo>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.hashCode() == newItem.hashCode()

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem == newItem
    }
}