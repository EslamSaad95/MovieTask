package com.app.movieTask.domain.usecase

import com.app.movieTask.domain.repositoy.TrendingMovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingMoviesUseCase @Inject constructor(private val trendingMovieRepo:TrendingMovieRepository) {

  suspend fun getTrendingMovies()= flow {
    emit(trendingMovieRepo.getTrendingMovie())
  }
}