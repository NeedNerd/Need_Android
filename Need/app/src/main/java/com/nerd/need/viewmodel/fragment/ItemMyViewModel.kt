package com.nerd.need.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nerd.need.data.model.response.PostResponse
import com.nerd.need.repository.PostRepository
import com.nerd.need.util.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class ItemMyViewModel : BaseViewModel() {private val repository = PostRepository()

    val getMyPostEvent = MutableLiveData<List<PostResponse>>()
    val getMyStateEvent = MutableLiveData<List<PostResponse>>()

    fun getMyPost(token: String){
        addDisposable(repository.getMyPost(token), object : DisposableSingleObserver<List<PostResponse>>(){
            override fun onSuccess(t: List<PostResponse>) {
                getMyPostEvent.value = t
                Log.d("result", "my post 가져오기 성공")
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                Log.d("result", "my post 가져오기 실패")
            }

        })
    }

    fun getMyStatePost(token: String, state: Int){
        addDisposable(repository.getMyPostState(token, state), object : DisposableSingleObserver<List<PostResponse>>(){
            override fun onSuccess(t: List<PostResponse>) {
                getMyStateEvent.value = t
                Log.d("result", "my state post 가져오기 성공")
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                Log.d("result", "my state post 가져오기 실패")
            }
        })
    }
}