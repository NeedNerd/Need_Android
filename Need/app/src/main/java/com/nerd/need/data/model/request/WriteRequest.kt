package com.nerd.need.data.model.request

data class WriteRequest(
    val content: String,
    val photo: String,
    val price: Int,
    val state: String,
    val title: String,
)
