package com.app.movieTask.data.mapper

import com.app.movieTask.data.network.dto.TrendingMoviesDto
import com.app.movieTask.domain.entity.TrendingMoviesEntity

fun List<TrendingMoviesDto.MovieItem>.toTrendingMovieEntity(): List<TrendingMoviesEntity> {
  return this.map {
    TrendingMoviesEntity(
      it.id,
      it.posterPath,
      it.originalTitle,
      it.releaseDate
    )
  }
}