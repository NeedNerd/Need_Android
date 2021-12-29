package com.nerd.need.data.model.response

data class PostResponse(
    val contact: String,
    val content: String,
    val date: String,
    val idx: Int,
    val local: String,
    val photo: String,
    val price: Int,
    val state: String,
    val title: String,
    val writer: String,
    val userIdx: Int,
)