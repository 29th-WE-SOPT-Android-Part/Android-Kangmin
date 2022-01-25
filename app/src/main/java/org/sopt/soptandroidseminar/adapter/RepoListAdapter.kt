package org.sopt.soptandroidseminar.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.api.data.response.ResponseRepo
import org.sopt.soptandroidseminar.api.data.response.ResponseUser
import org.sopt.soptandroidseminar.databinding.ItemRepoListBinding
import org.sopt.soptandroidseminar.util.MyDiffUtil
import java.util.*

class RepoListAdapter : ListAdapter<ResponseRepo, RepoListAdapter.RepoViewHolder>(DIFFUTIL){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        val binding =
            ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

//    interface OnStartDragListener {
//        fun onStartDrag(viewHolder: RepoViewHolder)
//    }
//
//    override fun onItemMove(fromPosition: Int, toPosition: Int) {
//        Collections.swap(repoList, fromPosition, toPosition)
//        notifyItemMoved(fromPosition, toPosition)
//    }
//
//    override fun onItemSwipe(position: Int) {
//        repoList.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    fun afterDragAndDrop() {
//        notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RepoViewHolder(
        private val binding: ItemRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(gitHubRepoInfo: ResponseRepo) {
            binding.repo = gitHubRepoInfo
        }
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<ResponseRepo>() {
            override fun areItemsTheSame(
                oldItem: ResponseRepo,
                newItem: ResponseRepo
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: ResponseRepo,
                newItem: ResponseRepo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}