package com.app.movieTask.presentation.utils.extensions

import android.widget.ImageView
import com.app.movieTask.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.app.movieTask.R

fun ImageView.load(imageUrl: String, radius: Int = 1) {
  Glide.with(this).load("${BuildConfig.Base_IMAGE_URL}${imageUrl}")
    .placeholder(R.drawable.ic_placeholder)
    . error(R.drawable.ic_placeholder)
    .into(this)
}