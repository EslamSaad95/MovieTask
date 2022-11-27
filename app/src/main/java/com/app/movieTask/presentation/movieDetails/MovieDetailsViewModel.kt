package com.app.movieTask.presentation.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieTask.domain.common.ApiFailure
import com.app.movieTask.domain.entity.MovieDetailsEntity
import com.app.movieTask.domain.usecase.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val useCase: MovieDetailsUseCase) : ViewModel() {

  private val _movieDetailsLiveData by lazy { MutableLiveData<MovieDetailsEntity>() }
  val movieDetailsLiveData get() = _movieDetailsLiveData

  private val _loadingLiveData by lazy { MutableLiveData<Boolean>() }
  val loadingLiveData get() = _loadingLiveData

  private val _errorLiveData by lazy { MutableLiveData<ApiFailure>() }
  val errorLiveData get() = _errorLiveData

  fun getMovieDetails(movieId: Int) {
    _loadingLiveData.value = true
    viewModelScope.launch {

      useCase.getMovieDetails(movieId).collect { response ->
        response.value?.let {
          _loadingLiveData.value = false
          _movieDetailsLiveData.value = it
        }
        response.error?.let {
          _loadingLiveData.value = false
          _errorLiveData.value = it
        }
      }
    }
  }
}