package com.nerd.need.repository

import com.nerd.need.data.Server
import com.nerd.need.data.model.request.WriteRequest
import com.nerd.need.data.model.response.PostResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import org.json.JSONObject

class PostRepository {
    fun uploadImage(token: String, image: MultipartBody.Part): Single<String> {
        return Server.postApi.uploadPhoto(token, image).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }

            it.body()!!.data
        }
    }

    fun writePost(token: String, request: WriteRequest): Single<Int>{
        return Server.postApi.post(token, request).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getDetailPost(token:String, idx: Int): Single<PostResponse>{
        return Server.postApi.getDetailPost(token, idx).map{
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getMyPost(token:String): Single<List<PostResponse>>{
        return Server.postApi.getMyPost(token).map{
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getMyPostState(token: String, state: Int): Single<List<PostResponse>>{
        return Server.postApi.getMyPostState(token, state).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }


    fun getAllPost(token:String): Single<List<PostResponse>>{
        return Server.postApi.getAllPost(token).map{
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getAllPostState(token: String, state: Int): Single<List<PostResponse>>{
        return Server.postApi.getAllPostState(token, state).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun modifyPost(token:String, idx: Int, state: String): Single<String>{
        return Server.postApi.modifyPost(token, idx, state).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            it.message()
        }
    }
}