package com.app.movieTask.presentation.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
  url?.let { view.load(it) }
}
