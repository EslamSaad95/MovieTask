package com.app.movieTask.presentation.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
  url?.let { view.load(it) }
}


@BindingAdapter("extractYear")
fun extractYearFromDate(view:MaterialTextView,date:String?) {
  date?.let {
    val formatter = SimpleDateFormat("yyyy", Locale.US)
    val dateDate: Date = formatter.parse(date)
    view.text= formatter.format(dateDate)
  }

}
