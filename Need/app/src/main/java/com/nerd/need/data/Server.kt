package com.nerd.need.data

import com.nerd.need.data.api.AuthApi
import com.nerd.need.data.api.PostApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Server {
    val okHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val serverRetrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(Constants.SERVER_HOST)
        .build()

    val authApi: AuthApi = serverRetrofit.create(AuthApi::class.java)
    val postApi: PostApi = serverRetrofit.create(PostApi::class.java)
}