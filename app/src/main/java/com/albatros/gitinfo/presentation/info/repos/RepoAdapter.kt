package com.albatros.gitinfo.presentation.info.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.albatros.gitinfo.databinding.RepoLayoutBinding
import com.albatros.gitinfo.domain.model.GithubRepoDetails

class RepoAdapter(
    val listener: OnItemClickListener
) : ListAdapter<GithubRepoDetails, RepoAdapter.RepoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = RepoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class RepoViewHolder(private val binding: RepoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: GithubRepoDetails) {
            with(binding) {
                name.text = repo.name

                root.setOnClickListener {
                    listener.onItemClick(repo)
                }
            }
        }
    }

    companion object {
        val diffCallback = DiffCallback()
    }

    interface OnItemClickListener {
        fun onItemClick(repo: GithubRepoDetails)
    }

    class DiffCallback : DiffUtil.ItemCallback<GithubRepoDetails>() {

        override fun areItemsTheSame(
            oldItem: GithubRepoDetails,
            newItem: GithubRepoDetails
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: GithubRepoDetails,
            newItem: GithubRepoDetails
        ) = oldItem == newItem
    }
}