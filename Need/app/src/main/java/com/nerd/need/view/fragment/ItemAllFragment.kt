package com.nerd.need.view.fragment

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nerd.need.R
import com.nerd.need.databinding.FragmentItemAllBinding
import com.nerd.need.util.SharedPreferenceManager
import com.nerd.need.util.base.BaseFragment
import com.nerd.need.view.adapter.PostListAdapter
import com.nerd.need.viewmodel.fragment.ItemAllViewModel

class ItemAllFragment : BaseFragment<FragmentItemAllBinding, ItemAllViewModel>() {
    override val viewModel: ItemAllViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.fragment_item_all

    var adapter: PostListAdapter? = null

    override fun observerViewModel() {
        mBinding.fragment = this
        initAdapter()

        with(mViewModel) {
            getAllPostEvent.observe(this@ItemAllFragment, Observer {
                adapter!!.submitList(it)
            })

            getAllStateEvent.observe(this@ItemAllFragment, Observer {
                adapter!!.submitList(it)
            })

            onErrorEvent.observe(this@ItemAllFragment, Observer {
                Toast.makeText(requireContext(), "글 목록을 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                Log.d("error", "ALL Post 중 오류 : $it")
            })
        }

    }

    private fun initAdapter() {
        adapter = PostListAdapter()
        adapter!!.context = requireContext()
        mBinding.mainRecyclerView.adapter = adapter

        val token = SharedPreferenceManager.getToken(requireContext())
        if (token != null) {
            mViewModel.getAllPost(token)
        } else {
            Toast.makeText(requireContext(), "토큰이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAllState(state: Int) {
        val token = SharedPreferenceManager.getToken(requireContext())
        if (token != null) {
            mViewModel.getAllStatePost(token, state)
        } else {
            Toast.makeText(requireContext(), "토큰이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }


    fun onClickBuyBtn() {
        mBinding.myWantBuyBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_want_buy)
        mBinding.myWantShareBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
        getAllState(0)
    }

    fun onClickShareBtn() {
        mBinding.myWantBuyBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
        mBinding.myWantShareBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_want_share)
        getAllState(1)
    }

}