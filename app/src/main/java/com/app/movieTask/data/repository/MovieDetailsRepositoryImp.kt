package com.app.movieTask.data.repository

import com.app.movieTask.data.common.map
import com.app.movieTask.data.mapper.toMovieDetailsEntity
import com.app.movieTask.data.network.ApiService
import com.app.movieTask.data.network.dto.GeneralErrorDto
import com.app.movieTask.domain.common.ApiFailure
import com.app.movieTask.domain.common.ApiResult
import com.app.movieTask.domain.entity.MovieDetailsEntity
import com.app.movieTask.domain.repositoy.MovieDetailsRepository
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailsRepositoryImp @Inject constructor(private val apiService: ApiService):MovieDetailsRepository {

  override suspend fun getMovieDetails(movieId:Int): ApiResult<MovieDetailsEntity, ApiFailure> {
    try {
      val response = apiService.getMovieDetails(movieId)
      if (response.isSuccessful)
        return ApiResult(value = response.body()?.toMovieDetailsEntity())
      else {
        val error = Gson().fromJson(
          response.errorBody()!!.charStream(),
          GeneralErrorDto::class.java
        )
        error?.let {
          return ApiResult(error = ApiFailure.ApiError(it.status_message))
        }
          ?: throw HttpException(response)

      }
    } catch (throwable: Throwable) {
      return ApiResult(error = throwable.map())
    }
  }
}