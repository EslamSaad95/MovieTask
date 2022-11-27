package com.app.movieTask.data.mapper

import com.app.movieTask.data.network.dto.MovieDetailsDto
import com.app.movieTask.domain.entity.MovieDetailsEntity

fun MovieDetailsDto.toMovieDetailsEntity():MovieDetailsEntity{
  return MovieDetailsEntity(
    this.posterPath,
    this.originalTitle,
    this.releaseDate,
    this.overview
  )
}