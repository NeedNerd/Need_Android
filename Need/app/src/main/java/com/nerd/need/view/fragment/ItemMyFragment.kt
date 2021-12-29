package com.nerd.need.view.fragment

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nerd.need.R
import com.nerd.need.databinding.FragmentItemMyBinding
import com.nerd.need.util.SharedPreferenceManager
import com.nerd.need.util.base.BaseFragment
import com.nerd.need.view.adapter.PostListAdapter
import com.nerd.need.viewmodel.fragment.ItemMyViewModel


class ItemMyFragment : BaseFragment<FragmentItemMyBinding, ItemMyViewModel>() {
    override val viewModel: ItemMyViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.fragment_item_my

    var adapter: PostListAdapter? = null

    override fun observerViewModel() {
        mBinding.fragment = this
        initAdapter()

        with(mViewModel) {
            getMyPostEvent.observe(this@ItemMyFragment, Observer {
                adapter!!.submitList(it)
            })
            getMyStateEvent.observe(this@ItemMyFragment, Observer {
                adapter!!.submitList(it)
            })
        }
    }

    private fun initAdapter() {
        adapter = PostListAdapter()
        adapter!!.context = requireContext()
        mBinding.mainRecyclerView.adapter = adapter

        val token = SharedPreferenceManager.getToken(requireContext())
        if (token != null) {
            mViewModel.getMyPost(token)
        } else {
            Toast.makeText(requireContext(), "토큰이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickBtn(btnIdx: Int) {
        initBtn()

        when (btnIdx) {
            0 -> {
                mBinding.myWantBuyBtn.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_want_buy)
                getMyStatePost(0)
            }
            1 -> {
                mBinding.myWantShareBtn.background =
                    ContextCompat.getDrawable(requireContext(),
                        R.drawable.background_tag_want_share)
                getMyStatePost(1)
            }
            2 -> {
                mBinding.myOkayBtn.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_okay)
                getMyStatePost(2)
            }
            3 -> {
                mBinding.myAlreadyBuyBtn.background =
                    ContextCompat.getDrawable(requireContext(),
                        R.drawable.background_tag_already_buy)
                getMyStatePost(3)
            }
            4 -> {
                mBinding.myAlreadyShareBtn.background =
                    ContextCompat.getDrawable(requireContext(),
                        R.drawable.background_tag_already_share)
                getMyStatePost(4)
            }
        }


    }

    private fun getMyStatePost(state: Int){
        val token = SharedPreferenceManager.getToken(requireContext())
        if (token != null) {
            mViewModel.getMyStatePost(token, state)
        } else {
            Toast.makeText(requireContext(), "토큰이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initBtn() {
        mBinding.myWantBuyBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
        mBinding.myWantShareBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
        mBinding.myOkayBtn.background = resources.getDrawable(R.drawable.background_disabled_button)
        mBinding.myAlreadyBuyBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
        mBinding.myAlreadyShareBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
    }

}