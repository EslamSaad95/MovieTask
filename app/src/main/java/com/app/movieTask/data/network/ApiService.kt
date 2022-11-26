package com.app.movieTask.data.network

import com.app.movieTask.data.network.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

  @GET("discover/movie")
  suspend fun getTrendingMovies(): Response<MoviesDto>
}