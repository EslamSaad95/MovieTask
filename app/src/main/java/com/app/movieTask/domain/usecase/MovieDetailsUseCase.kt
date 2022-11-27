package com.app.movieTask.domain.usecase

import com.app.movieTask.domain.repositoy.MovieDetailsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository) {

  suspend fun getMovieDetails(movieId: Int) = flow {
    emit(repository.getMovieDetails(movieId))
  }
}