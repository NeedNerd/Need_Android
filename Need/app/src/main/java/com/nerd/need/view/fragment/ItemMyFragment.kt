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
    var lastState = -1

    override fun onResume() {
        super.onResume()
        checkMyPostState()

    }

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
        getMyPost()
    }

    private fun getMyPost() {
        val token = SharedPreferenceManager.getToken(requireContext())
        if (token != null) {
            mViewModel.getMyPost(token)
        } else {
            Toast.makeText(requireContext(), "토큰이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickBtn(btnIdx: Int) {
        initBtn()

        if (btnIdx == lastState) {
            lastState = -1
            checkMyPostState()
        } else {
            when (btnIdx) {
                0 -> {
                    mBinding.myWantBuyBtn.background =
                        ContextCompat.getDrawable(requireContext(),
                            R.drawable.background_tag_want_buy)
                    lastState = 0
                    checkMyPostState()
                }
                1 -> {
                    mBinding.myWantShareBtn.background =
                        ContextCompat.getDrawable(requireContext(),
                            R.drawable.background_tag_want_share)
                    lastState = 1
                    checkMyPostState()
                }
                2 -> {
                    mBinding.myOkayBtn.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_okay)
                    lastState = 2
                    checkMyPostState()
                }
                3 -> {
                    mBinding.myAlreadyBuyBtn.background =
                        ContextCompat.getDrawable(requireContext(),
                            R.drawable.background_tag_already_buy)
                    lastState = 3
                    checkMyPostState()
                }
                4 -> {
                    mBinding.myAlreadyShareBtn.background =
                        ContextCompat.getDrawable(requireContext(),
                            R.drawable.background_tag_already_share)
                    lastState = 4
                    checkMyPostState()
                }
            }
        }
    }

    private fun checkMyPostState() {
        val token = SharedPreferenceManager.getToken(requireContext())
        if (token != null) {
            if (lastState >= 0) {
                mViewModel.getMyStatePost(token, lastState)
            } else {
                mViewModel.getMyPost(token)
            }
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