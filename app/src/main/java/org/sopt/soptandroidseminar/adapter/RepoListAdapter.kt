package org.sopt.soptandroidseminar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.api.data.response.ResponseRepoInfo
import org.sopt.soptandroidseminar.api.data.response.ResponseUserInfo
import org.sopt.soptandroidseminar.databinding.ItemRepoListBinding
import org.sopt.soptandroidseminar.util.MyDiffUtil
import org.sopt.soptandroidseminar.util.MyTouchHelperCallback
import java.util.*

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>(),
    MyTouchHelperCallback.OnItemMoveListener {

    private val repoList = mutableListOf<ResponseRepoInfo>()

    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        val binding =
            ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int = repoList.size

    fun setItems(newItems: List<ResponseRepoInfo>) {
        val diffUtil = MyDiffUtil(repoList, newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        repoList.clear()
        repoList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RepoViewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(repoList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        repoList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun afterDragAndDrop() {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    class RepoViewHolder(
        private val binding: ItemRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(gitHubRepoInfo: ResponseRepoInfo) {
            binding.apply {
                repoName.text = gitHubRepoInfo.name
                repoContext.text = gitHubRepoInfo.description
                repoLanguage.text = gitHubRepoInfo.language
            }
        }
    }

    interface ItemClickListener {
        fun onClick(responseUserInfo: ResponseUserInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}