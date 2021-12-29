package com.nerd.need.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nerd.need.R
import com.nerd.need.databinding.ActivitySignupBinding
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.viewmodel.activity.SignupViewModel

class SignupActivity : BaseActivity<ActivitySignupBinding, SignupViewModel>() {
    override val viewModel: SignupViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_signup

    override fun observerViewModel() {
        mBinding.activity = this
        with(mViewModel){
            registerEvent.observe(this@SignupActivity, Observer {
                Toast.makeText(this@SignupActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                finish()
            })

            onErrorEvent.observe(this@SignupActivity, Observer {
                Toast.makeText(this@SignupActivity, "회원가입에 실패했습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show()
            })
        }
    }

    fun onClickBackBtn(){
        finish()
    }
}