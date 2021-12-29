package com.nerd.need.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nerd.need.R
import com.nerd.need.data.Constants

object BindingAdapter {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun imageLoad(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load("${Constants.SERVER_HOST}photo/${url}")
            .error(R.drawable.ic_logo)
            .placeholder(R.drawable.ic_logo)
            .override(50, 50)
            .into(imageView)
    }

    @BindingAdapter("app:imageUrlBig")
    @JvmStatic
    fun imageLoadBig(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context)
                .load("${Constants.SERVER_HOST}photo/${url}")
                .error(R.drawable.ic_logo)
                .placeholder(R.drawable.ic_logo)
                .override(400, 180)
                .into(imageView)
        }
    }


    @BindingAdapter("app:tagCheck")
    @JvmStatic
    fun tagCheck(tv: TextView, state: String?) {
        when (state) {
            "0" -> {
                tv.setText(R.string.text_want_buy)
                tv.background =
                    ContextCompat.getDrawable(tv.context, R.drawable.background_tag_want_buy)
            }
            "1" -> {
                tv.setText(R.string.text_want_share)
                tv.background =
                    ContextCompat.getDrawable(tv.context, R.drawable.background_tag_want_share)
            }
            "2" -> {
                tv.setText(R.string.text_okay)
                tv.background =
                    ContextCompat.getDrawable(tv.context, R.drawable.background_tag_okay)
            }

            "3" -> {
                tv.setText(R.string.text_already_buy)
                tv.background =
                    ContextCompat.getDrawable(tv.context, R.drawable.background_tag_already_buy)
            }
            "4" -> {
                tv.setText(R.string.text_already_share)
                tv.background =
                    ContextCompat.getDrawable(tv.context, R.drawable.background_tag_already_share)
            }
        }
    }
}