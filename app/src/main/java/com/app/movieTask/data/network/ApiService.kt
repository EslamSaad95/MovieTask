package com.app.movieTask.data.network

import com.app.movieTask.data.network.dto.TrendingMoviesDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

  @GET("discover/movie")
  suspend fun getTrendingMovies(): Response<TrendingMoviesDto>
}