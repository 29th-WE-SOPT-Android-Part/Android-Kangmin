package org.sopt.soptandroidseminar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.soptandroidseminar.databinding.ItemRepoListBinding
import org.sopt.soptandroidseminar.domain.entity.GithubRepo

class RepoListAdapter : ListAdapter<GithubRepo, RepoListAdapter.RepoViewHolder>(DIFFUTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        val binding =
            ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RepoViewHolder(
        private val binding: ItemRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(gitHubRepo: GithubRepo) {
            binding.repo = gitHubRepo
        }
    }

    companion object {
        private val DIFFUTIL = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(
                oldItem: GithubRepo,
                newItem: GithubRepo
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: GithubRepo,
                newItem: GithubRepo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}