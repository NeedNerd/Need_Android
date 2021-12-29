package com.nerd.need.view.activity

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.nerd.need.R
import com.nerd.need.databinding.ActivityMainBinding
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.view.fragment.ItemAllFragment
import com.nerd.need.view.fragment.ItemMyFragment
import com.nerd.need.viewmodel.activity.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun observerViewModel() {
        initTabLayout()
        mBinding.activity = this

        with(mViewModel){

        }
    }

    fun onClickWriteBtn(){
        val intent = Intent(this, WriteActivity::class.java)
        startActivity(intent)
    }

    private fun initTabLayout() {
        val itemAllFragment = ItemAllFragment()
        val itemMyFragment = ItemMyFragment()

        supportFragmentManager.beginTransaction().add(R.id.main_frame, itemAllFragment).commit()

        mBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, itemAllFragment).commit()
                    }
                    1->{
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, itemMyFragment).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}