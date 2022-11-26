package com.app.movieTask.data.repository

import com.app.movieTask.data.common.map
import com.app.movieTask.data.mapper.toTrendingMovieEntity
import com.app.movieTask.data.network.ApiService
import com.app.movieTask.data.network.dto.GeneralErrorDto
import com.app.movieTask.domain.common.ApiFailure
import com.app.movieTask.domain.common.ApiResult
import com.app.movieTask.domain.entity.TrendingMoviesEntity
import com.app.movieTask.domain.repositoy.TrendingMovieRepository
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

class TrendingMoviesRepositoryImp @Inject constructor(private val apiService: ApiService):TrendingMovieRepository {

  override suspend fun getTrendingMovie(): ApiResult<List<TrendingMoviesEntity>, ApiFailure> {
    try {
      val response = apiService.getTrendingMovies()
      if (response.isSuccessful)
        return ApiResult(value = response.body()?.results?.toTrendingMovieEntity())
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