package com.nerd.need.view.activity

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nerd.need.R
import com.nerd.need.databinding.ActivityLoginBinding
import com.nerd.need.util.SharedPreferenceManager
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.viewmodel.activity.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun observerViewModel() {
        mBinding.activity = this
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        with(mViewModel) {
            loginEvent.observe(this@LoginActivity, Observer {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)

                SharedPreferenceManager.setToken(this@LoginActivity, it)

                startActivity(intent)
                finish()
            })

            onErrorEvent.observe(this@LoginActivity, Observer {
                Toast.makeText(this@LoginActivity, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                Log.e("error", "오류 발생!")
            })
        }
    }

    fun onClickSignUp() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}