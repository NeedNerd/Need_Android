package com.nerd.need.view.activity

import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nerd.need.R
import com.nerd.need.databinding.ActivityDetailBinding
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.viewmodel.activity.DetailViewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override val viewModel: DetailViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_detail

    override fun observerViewModel() {
        mBinding.activity = this
        with(mViewModel){

        }
    }
}