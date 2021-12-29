package com.nerd.need.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nerd.need.data.model.request.RegisterRequest
import com.nerd.need.repository.AuthRepository
import com.nerd.need.util.SingleLiveEvent
import com.nerd.need.util.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class SignupViewModel : BaseViewModel() {

    val repository = AuthRepository()

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val area = MutableLiveData<String>()

    val registerEvent = SingleLiveEvent<Any>()

    fun register() {
        if (!id.equals("") && !password.equals("") && !name.equals("") && !phone.equals("") && !area.equals("")) {
            val request = RegisterRequest(phone.value!!,
                id.value!!,
                area.value!!,
                name.value!!,
                password.value!!)

            addDisposable(repository.register(request),
                object : DisposableSingleObserver<String>() {
                    override fun onSuccess(t: String) {
                        Log.d("result", "로그인 성공")
                        registerEvent.call()
                    }

                    override fun onError(e: Throwable) {
                        Log.d("result", "로그인 성공")
                        onErrorEvent.value = e
                    }
                })
        }
    }
}