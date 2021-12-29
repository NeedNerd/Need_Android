package com.nerd.need.view.fragment

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nerd.need.R
import com.nerd.need.databinding.FragmentItemAllBinding
import com.nerd.need.util.base.BaseFragment
import com.nerd.need.viewmodel.fragment.ItemAllViewModel

class ItemAllFragment : BaseFragment<FragmentItemAllBinding, ItemAllViewModel>() {
    override val viewModel: ItemAllViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.fragment_item_all

    override fun observerViewModel() {
        mBinding.fragment = this
        with(mViewModel) {

        }
    }

    fun onClickBuyBtn() {
        mBinding.myWantBuyBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_want_buy)
        mBinding.myWantShareBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
    }

    fun onClickShareBtn() {
        mBinding.myWantBuyBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_disabled_button)
        mBinding.myWantShareBtn.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_want_share)
    }

}