package com.app.movieTask.domain.repositoy

import com.app.movieTask.domain.common.ApiFailure
import com.app.movieTask.domain.common.ApiResult
import com.app.movieTask.domain.entity.TrendingMoviesEntity

interface MovieDetailsRepository {

  suspend fun getMovieDetails(): ApiResult<List<TrendingMoviesEntity>, ApiFailure>
}