package com.nerd.need.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nerd.need.data.model.response.PostResponse
import com.nerd.need.repository.PostRepository
import com.nerd.need.util.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class ItemAllViewModel: BaseViewModel() {
    private val repository = PostRepository()

    val getAllPostEvent = MutableLiveData<List<PostResponse>>()
    val getAllStateEvent = MutableLiveData<List<PostResponse>>()

    fun getAllPost(token: String){
        addDisposable(repository.getAllPost(token), object : DisposableSingleObserver<List<PostResponse>>(){
            override fun onSuccess(t: List<PostResponse>) {
                getAllPostEvent.value = t
                Log.d("result", "모든 post 가져오기 성공")
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                Log.d("result", "모든 post 가져오기 실패")
            }

        })
    }

    fun getAllStatePost(token: String, state: Int){
        addDisposable(repository.getAllPostState(token, state), object : DisposableSingleObserver<List<PostResponse>>(){
            override fun onSuccess(t: List<PostResponse>) {
                getAllStateEvent.value = t
                Log.d("result", "모든 state post 가져오기 성공")
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                Log.d("result", "모든 state post 가져오기 실패")
            }
        })
    }
}