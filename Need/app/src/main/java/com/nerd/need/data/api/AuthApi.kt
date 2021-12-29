package com.nerd.need.data.api

import com.nerd.need.data.model.request.LoginRequest
import com.nerd.need.data.model.request.RegisterRequest
import com.nerd.need.data.model.response.BaseResponse
import com.nerd.need.data.model.response.LoginResponse
import com.nerd.need.data.model.response.MyInfoReponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Single<Response<BaseResponse<String>>>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Single<Response<BaseResponse<Any>>>

    @GET("auth/my")
    fun getMyIf(@Header("Authorization") token: String): Single<Response<BaseResponse<MyInfoReponse>>>
}