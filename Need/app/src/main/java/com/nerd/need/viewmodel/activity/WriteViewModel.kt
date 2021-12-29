package com.nerd.need.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nerd.need.data.model.request.WriteRequest
import com.nerd.need.repository.PostRepository
import com.nerd.need.util.base.BaseViewModel
import io.reactivex.observers.DisposableSingleObserver
import okhttp3.MultipartBody

class WriteViewModel : BaseViewModel() {
    private val repository = PostRepository()

    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val price = MutableLiveData<String>(0.toString())

    val uploadImageEvent = MutableLiveData<String>()
    val writePostEvent = MutableLiveData<Int>()

    fun uploadImage(token: String, imageFile: MultipartBody.Part) {
        addDisposable(repository.uploadImage(token, imageFile),
            object : DisposableSingleObserver<String>() {
                override fun onSuccess(t: String) {
                    Log.d("result", "이미지 업로드 성공")
                    uploadImageEvent.value = t
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    Log.d("result", "이미지 업로드 실패")
                }

            })
    }

    fun writePost(image: String, state: String, token: String) {
        val priceToInt = Integer.parseInt(price.value)

        if (!content.equals("") && !title.equals("")) {
            val request = WriteRequest(content.value!!, image, priceToInt, state, title.value!!)

            addDisposable(repository.writePost(token, request),
                object : DisposableSingleObserver<Int>() {
                    override fun onSuccess(t: Int) {
                        writePostEvent.value = t
                        Log.d("result", "게시글 작성 성공")
                    }

                    override fun onError(e: Throwable) {
                        onErrorEvent.value = e
                        Log.d("result", "게시글 작성 실패")
                    }

                })
        }

    }
}