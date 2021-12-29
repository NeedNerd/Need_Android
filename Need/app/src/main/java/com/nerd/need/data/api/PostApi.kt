package com.nerd.need.data.api

import com.nerd.need.data.model.request.WriteRequest
import com.nerd.need.data.model.response.BaseResponse
import com.nerd.need.data.model.response.PostResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PostApi {
    @Multipart
    @POST("photo/")
    fun uploadPhoto(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
    ): Single<Response<BaseResponse<String>>>

    @POST("post/")
    fun post(
        @Header("Authorization") token: String,
        @Body request: WriteRequest,
    ): Single<Response<BaseResponse<Int>>>

    @GET("post/{idx}")
    fun getDetailPost(
        @Header("Authorization") token: String,
        @Path("idx") idx: Int,
    ): Single<Response<BaseResponse<PostResponse>>>

    @GET("post/my")
    fun getMyPost(@Header("Authorization") token: String): Single<Response<BaseResponse<List<PostResponse>>>>

    @GET("post/my")
    fun getMyPostState(
        @Header("Authorization") token: String,
        @Query("state") state: Int,
    ): Single<Response<BaseResponse<List<PostResponse>>>>

    @GET("post/valid")
    fun getAllPost(@Header("Authorization") token: String): Single<Response<BaseResponse<List<PostResponse>>>>

    @GET("post/valid")
    fun getAllPostState(
        @Header("Authorization") token: String,
        @Query("state") state: Int,
    ): Single<Response<BaseResponse<List<PostResponse>>>>

    @PATCH("post/{idx}")
    fun modifyPost(@Header("Authorization") token: String, @Path("idx") idx: Int, @Query("state") state: String): Single<Response<BaseResponse<Any>>>
}