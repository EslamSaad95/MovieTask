package com.app.movieTask.data.network.dto

import com.google.gson.annotations.SerializedName

data class GeneralErrorDto (
  @SerializedName("status_code")
  val status_code:Int,
  @SerializedName("status_message")
  val status_message:String,
  @SerializedName("success")
  val success:Boolean
    )