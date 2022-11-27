package com.app.movieTask.data.network

import com.app.movieTask.data.network.dto.MovieDetailsDto
import com.app.movieTask.data.network.dto.TrendingMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("discover/movie")
  suspend fun getTrendingMovies(): Response<TrendingMoviesDto>

  @GET("movie/{movieId}")
  suspend fun getMovieDetails(@Path("movieId")movieId:Int):Response<MovieDetailsDto>

}