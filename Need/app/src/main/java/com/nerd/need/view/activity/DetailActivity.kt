package com.nerd.need.view.activity

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nerd.need.R
import com.nerd.need.data.model.response.PostResponse
import com.nerd.need.databinding.ActivityDetailBinding
import com.nerd.need.util.SharedPreferenceManager
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.viewmodel.activity.DetailViewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override val viewModel: DetailViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_detail

    private var postIdx = 0
    private var postData: PostResponse? = null

    override fun observerViewModel() {
        mBinding.activity = this
        //mBinding.item = PostResponse("","","",0, "","",0,"","","",0)
        initDetail()

        with(mViewModel) {
            detailPostEvent.observe(this@DetailActivity, Observer {
                mBinding.item = it
                postData = it
                startMyInfo()
            })

            myInfoEvent.observe(this@DetailActivity, Observer {
                if (it.userIdx == postData?.userIdx) {
                    mBinding.stateChangeView.visibility = View.VISIBLE
                } else {
                    mBinding.stateChangeView.visibility = View.INVISIBLE
                }
            })

            modifyEvent.observe(this@DetailActivity, Observer {
                Toast.makeText(this@DetailActivity, "게시글 상태 변경 성공", Toast.LENGTH_SHORT)
                    .show()
                getDetailData(postData?.idx!!)
            })

            onErrorEvent.observe(this@DetailActivity, Observer {
                Log.d("error", "$it")
                Toast.makeText(this@DetailActivity, "상세 정보를 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT)
                    .show()
            })
        }
    }

    private fun startMyInfo() {

        val token = SharedPreferenceManager.getToken(this)

        if (token != null) {
            mViewModel.getMyInfo(token)
        } else {
            Toast.makeText(this@DetailActivity, "토큰이 만료되었습니다.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun initDetail() {
        postIdx = intent?.getIntExtra("postIdx", 0)!!

        Log.d("sdaf", postIdx.toString())
        getDetailData(postIdx)
    }

    private fun getDetailData(postIdx: Int) {
        val token = SharedPreferenceManager.getToken(this)

        if (token != null) {
            mViewModel.getDetailPost(token, this.postIdx)
        } else {
            Toast.makeText(this@DetailActivity, "토큰이 만료되었습니다.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun changePostState(state: String) {
        val token = SharedPreferenceManager.getToken(this)

        if (token != null) {
            if(postData != null){
                mViewModel.modifyPost(token, postData?.idx!!, state)
            } else {
                Toast.makeText(this@DetailActivity, "post idx가 존재하지 않습니다..", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this@DetailActivity, "토큰이 만료되었습니다.", Toast.LENGTH_SHORT)
                .show()
        }
    }


    fun onClickBackBtn() {
        finish()
    }
}