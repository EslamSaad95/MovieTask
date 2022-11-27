package com.app.movieTask.presentation.utils.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun extractYearFromDate(date:String): String {

  val formatter = SimpleDateFormat("yyyy", Locale.US)
  val dateDate: Date = formatter.parse(date)

  return formatter.format(dateDate)
}