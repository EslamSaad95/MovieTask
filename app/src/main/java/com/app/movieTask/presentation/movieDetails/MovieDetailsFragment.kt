package com.app.movieTask.presentation.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.app.movieTask.R
import com.app.movieTask.databinding.FragmentMovieDetailsBinding
import com.app.movieTask.domain.common.ApiFailure
import com.app.movieTask.presentation.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

private  val binding by lazy { FragmentMovieDetailsBinding.inflate(layoutInflater) }
 private val viewModel by viewModels<MovieDetailsViewModel>()




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
      viewModel = this@MovieDetailsFragment.viewModel
    }
    viewModel.getMovieDetails(arguments?.getInt("movieId")?:0)
    observerErrorLiveData()
    observeMovieDetailsLiveData()
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

  private fun observeMovieDetailsLiveData() {
    viewModel.movieDetailsLiveData.observe(viewLifecycleOwner) {
      binding.movie = it
    }
  }
}