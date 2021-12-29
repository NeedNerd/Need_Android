package com.nerd.need.repository

import android.util.Log
import com.nerd.need.data.Server
import com.nerd.need.data.model.request.LoginRequest
import com.nerd.need.data.model.request.RegisterRequest
import com.nerd.need.data.model.response.LoginResponse
import com.nerd.need.data.model.response.MyInfoReponse
import io.reactivex.Single
import org.json.JSONObject

class AuthRepository {
    fun login(request: LoginRequest): Single<String> {
        return Server.authApi.login(request).map {
            Log.d("middle", it.isSuccessful.toString())

            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }

            it.body()!!.data
        }
    }

    fun register(request: RegisterRequest): Single<String> {
        return Server.authApi.register(request).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }

            it.message()
        }
    }

    fun getMyInfo(token: String): Single<MyInfoReponse>{
        return Server.authApi.getMyIf(token).map {

            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }

            it.body()!!.data
        }
    }
}