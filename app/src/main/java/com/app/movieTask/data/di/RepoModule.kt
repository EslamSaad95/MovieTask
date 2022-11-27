package com.app.movieTask.data.di

import com.app.movieTask.data.repository.MovieDetailsRepositoryImp
import com.app.movieTask.data.repository.TrendingMoviesRepositoryImp
import com.app.movieTask.domain.repositoy.MovieDetailsRepository
import com.app.movieTask.domain.repositoy.TrendingMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

  @Singleton
  @Binds
  abstract fun provideTrendingMovie(repoImpl: TrendingMoviesRepositoryImp): TrendingMovieRepository

  @Singleton
  @Binds
  abstract fun provideMovieDetails(repoImpl: MovieDetailsRepositoryImp): MovieDetailsRepository
}