package com.app.movieTask.Presentation.trendingMovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieTask.domain.common.ApiFailure
import com.app.movieTask.domain.entity.TrendingMoviesEntity
import com.app.movieTask.domain.usecase.TrendingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(private val useCase:TrendingMoviesUseCase) :ViewModel() {

  private val _trendingMoviesLiveData by lazy { MutableLiveData<List<TrendingMoviesEntity>>() }
  val trendingMoviesLiveData get() = _trendingMoviesLiveData

  private val _loadingLiveData by lazy { MutableLiveData<Boolean>() }
  val loadingLiveData get() = _loadingLiveData

  private val _errorLiveData by lazy { MutableLiveData<ApiFailure>() }
  val errorLiveData get() = _errorLiveData

  init {
    getTrendingMovies()
  }

  private fun getTrendingMovies()
  {
    _loadingLiveData.value=true
    viewModelScope.launch {
      useCase.getTrendingMovies().collect{response->
          response.value?.let {
          _loadingLiveData.value=false
            _trendingMoviesLiveData.value=it
          }

        response.error?.let {
          _loadingLiveData.value=false
          _errorLiveData.value=it
        }
      }
    }
  }
}