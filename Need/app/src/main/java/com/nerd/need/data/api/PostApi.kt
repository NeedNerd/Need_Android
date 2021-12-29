package com.nerd.need.data.api

import com.nerd.need.data.model.request.WriteRequest
import com.nerd.need.data.model.response.BaseResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PostApi {
    @Multipart
    @POST("photo/")
    fun uploadPhoto(@Header("Authorization") token: String,  @Part file: MultipartBody.Part): Single<Response<BaseResponse<String>>>

    @POST("post/")
    fun post(@Header("Authorization") token: String, @Body request: WriteRequest): Single<Response<BaseResponse<Int>>>
}