package com.nerd.need.data.model.request

data class RegisterRequest(
    val contact: String,
    val id: String,
    val local: String,
    val name: String,
    val password: String,
)
