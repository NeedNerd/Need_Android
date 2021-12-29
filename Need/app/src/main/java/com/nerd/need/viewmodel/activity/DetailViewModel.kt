package com.nerd.need.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nerd.need.data.model.response.MyInfoReponse
import com.nerd.need.data.model.response.PostResponse
import com.nerd.need.repository.AuthRepository
import com.nerd.need.repository.PostRepository
import com.nerd.need.util.SingleLiveEvent
import com.nerd.need.util.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver

class DetailViewModel : BaseViewModel() {
    private val postRepository = PostRepository()
    private val authRepository = AuthRepository()
    val detailPostEvent = MutableLiveData<PostResponse>()
    val myInfoEvent = MutableLiveData<MyInfoReponse>()
    val modifyEvent = SingleLiveEvent<Any>()

    fun getDetailPost(token: String, postIdx: Int) {
        addDisposable(postRepository.getDetailPost(token, postIdx),
            object : DisposableSingleObserver<PostResponse>() {
                override fun onSuccess(t: PostResponse) {
                    detailPostEvent.value = t
                    Log.d("result", "상세 정보 성공")
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    Log.d("result", "상세 정보 문제 발생")
                }

            })
    }

    fun getMyInfo(token: String){
        addDisposable(authRepository.getMyInfo(token), object : DisposableSingleObserver<MyInfoReponse>(){
            override fun onSuccess(t: MyInfoReponse) {
                myInfoEvent.value = t
                Log.d("result", "내 정보 조회 성공")
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                Log.d("result", "내 정보 문제 발생")
            }

        })
    }

    fun modifyPost(token: String, idx: Int, state: String){
        addDisposable(postRepository.modifyPost(token, idx, state), object : DisposableSingleObserver<Any>(){
            override fun onSuccess(t: Any) {
                modifyEvent.value = t
                Log.d("result", "수정 성공")
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
                Log.d("result", "수정 실패")
            }

        })
    }
}