package com.app.movieTask.domain.repositoy

import com.app.movieTask.domain.entity.TrendingMoviesEntity

interface TrendingMovieRepository {

  suspend fun getTrendingMovie():List<TrendingMoviesEntity>

}