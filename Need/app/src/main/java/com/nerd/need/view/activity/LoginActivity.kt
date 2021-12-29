package com.nerd.need.view.activity

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nerd.need.R
import com.nerd.need.databinding.ActivityLoginBinding
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.viewmodel.activity.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun observerViewModel() {
        mBinding.activity = this
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        with(mViewModel){

        }
    }

    fun onClickSignUp(){
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}