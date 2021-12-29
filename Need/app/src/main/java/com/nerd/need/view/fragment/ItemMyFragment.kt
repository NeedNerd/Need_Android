package com.nerd.need.view.fragment

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.nerd.need.R
import com.nerd.need.databinding.FragmentItemMyBinding
import com.nerd.need.util.base.BaseFragment
import com.nerd.need.viewmodel.fragment.ItemMyViewModel


class ItemMyFragment : BaseFragment<FragmentItemMyBinding, ItemMyViewModel>() {
    override val viewModel: ItemMyViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.fragment_item_my

    override fun observerViewModel() {
        mBinding.fragment = this

        with(mViewModel) {

        }
    }

    fun onClickBtn(btnIdx: Int) {
        initBtn()

        when (btnIdx) {
            0 -> mBinding.myWantBuyBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_want_buy)
            1 -> mBinding.myWantShareBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_want_share)
            2 -> mBinding.myOkayBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_okay)
            3 -> mBinding.myAlreadyBuyBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_already_buy)
            4 -> mBinding.myAlreadyShareBtn.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.background_tag_already_share)
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