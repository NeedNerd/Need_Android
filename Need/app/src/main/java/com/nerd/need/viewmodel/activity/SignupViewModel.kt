package com.nerd.need.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import com.nerd.need.util.base.BaseViewModel

class SignupViewModel : BaseViewModel() {

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val area = MutableLiveData<String>()


}