package com.nerd.need.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import com.nerd.need.util.base.BaseViewModel

class LoginViewModel : BaseViewModel() {
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
}