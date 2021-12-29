package com.nerd.need.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nerd.need.R
import com.nerd.need.data.model.response.PostResponse
import com.nerd.need.databinding.MainItemBinding
import com.nerd.need.util.DiffUtil
import com.nerd.need.view.activity.DetailActivity

class PostListAdapter : ListAdapter<PostResponse, PostListAdapter.ViewHolder>(DiffUtil.diffUtil) {

    var context: Context ?= null

    inner class ViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostResponse) {
            binding.item = item
            binding.itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("postIdx", item.idx)
                context?.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MainItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.main_item,
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}