package com.nerd.need.viewmodel.activity

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.nerd.need.data.model.request.LoginRequest
import com.nerd.need.data.model.response.BaseResponse
import com.nerd.need.data.model.response.LoginResponse
import com.nerd.need.repository.AuthRepository
import com.nerd.need.util.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class LoginViewModel : BaseViewModel() {
    private val repository = AuthRepository()

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val loginEvent = MutableLiveData<String>()

    fun login(view: View) {
        Log.d("running", "로그인")

        if (id.value != null && password.value != null) {
            Log.d("running", "로그인 시작")
            val request = LoginRequest(id.value!!, password.value!!)

            addDisposable(repository.login(request),
                object : DisposableSingleObserver<String>() {
                    override fun onSuccess(t: String) {
                        loginEvent.value = t
                        Log.d("result", "로그인 결과 $t")
                    }

                    override fun onError(e: Throwable) {
                        onErrorEvent.value = e
                        Log.d("result", "로그인 실패")
                    }
                })
        }
    }
}