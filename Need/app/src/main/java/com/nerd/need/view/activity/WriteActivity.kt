package com.nerd.need.view.activity

import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.nerd.need.R
import com.nerd.need.databinding.ActivityWriteBinding
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.viewmodel.activity.WriteViewModel

class WriteActivity : BaseActivity<ActivityWriteBinding, WriteViewModel>() {
    override val viewModel: WriteViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_write

    override fun observerViewModel() {
        with(mViewModel) {

        }
    }

    fun onClickBackBtn() {
        finish()
    }

    fun onClickImageBtn() {

    }

    fun onClickWantBuyBtn() {
        mBinding.writeBuyBtn.background = ContextCompat.getDrawable(this, R.drawable.background_tag_want_buy)
        mBinding.writeShareBtn.background =
            ContextCompat.getDrawable(this, R.drawable.background_disabled_button)

        mBinding.writeInputPrice.visibility = View.VISIBLE
    }

    fun onClickWantShareBtn() {
        mBinding.writeBuyBtn.background =
            ContextCompat.getDrawable(this, R.drawable.background_disabled_button)
        mBinding.writeShareBtn.background =
            ContextCompat.getDrawable(this, R.drawable.background_tag_want_share)

        mBinding.writeInputPrice.visibility = View.INVISIBLE
    }

}