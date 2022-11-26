package com.app.movieTask.domain.repositoy

import com.app.movieTask.domain.common.ApiFailure
import com.app.movieTask.domain.common.ApiResult
import com.app.movieTask.domain.entity.TrendingMoviesEntity

interface TrendingMovieRepository {

  suspend fun getTrendingMovie():ApiResult<List<TrendingMoviesEntity>,ApiFailure>

}