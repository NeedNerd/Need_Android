package com.nerd.need.data.model.response

data class BaseResponse<T>(
    val message: String,
    val code: Int,
    val data: T,
)