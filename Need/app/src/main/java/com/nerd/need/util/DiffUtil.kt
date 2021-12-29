package com.nerd.need.util

import androidx.recyclerview.widget.DiffUtil
import com.nerd.need.data.model.response.PostResponse

object DiffUtil {
    val diffUtil = object : DiffUtil.ItemCallback<PostResponse>() {
        override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean =
            oldItem.idx == newItem.idx

        override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean =
            oldItem == newItem
    }

}