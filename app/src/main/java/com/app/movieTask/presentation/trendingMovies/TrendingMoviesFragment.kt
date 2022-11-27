package com.app.movieTask.presentation.trendingMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.movieTask.presentation.trendingMovies.adapter.TrendingMoviesRvAdapter
import com.app.movieTask.presentation.utils.extensions.linearLayoutManager
import com.app.movieTask.presentation.utils.extensions.visible
import com.app.movieTask.R
import com.app.movieTask.databinding.FragmentTrendingMoviesBinding
import com.app.movieTask.domain.common.ApiFailure
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingMoviesFragment : Fragment() {

  private val viewModel by viewModels<TrendingMoviesViewModel>()
  private val binding by lazy { FragmentTrendingMoviesBinding.inflate(layoutInflater) }
  private val trendingMoviesAdapter by lazy { TrendingMoviesRvAdapter() }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {
      lifecycleOwner = viewLifecycleOwner
      viewModel = this@TrendingMoviesFragment.viewModel
    }



    observerTrendingMovies()
    observerErrorLiveData()
  }

  private fun observerErrorLiveData() {
    viewModel.errorLiveData.observe(viewLifecycleOwner) {

      if (it is ApiFailure.ConnectionError)
        binding.tvErrorMsg.setCompoundDrawablesWithIntrinsicBounds(
          0,
          R.drawable.ic_noconnection,
          0,
          0
        )
      else
        binding.tvErrorMsg.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
      binding.tvErrorMsg.apply {
        visible()
        it.error?.let { text = it }
        it.errorResId?.let { text = getText(it) }
      }
    }
  }

  private fun observerTrendingMovies() {
    viewModel.trendingMoviesLiveData.observe(viewLifecycleOwner) {
      it?.let {
        trendingMoviesAdapter.fill(it)
        initTrendingMoviesRv()
      }
    }
  }

  private fun initTrendingMoviesRv() {
    binding.rvMovies.apply {
      linearLayoutManager()
      adapter = trendingMoviesAdapter
      trendingMoviesAdapter.setOnClickListener { _, item, _ ->
   findNavController().navigate(R.id.actionTrendingMoviesToMovieDetails, bundleOf("movieId" to item.movieId))

      }
    }
  }
}