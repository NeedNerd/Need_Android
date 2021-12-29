package com.nerd.need.repository

import com.nerd.need.data.Server
import com.nerd.need.data.model.request.WriteRequest
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
}