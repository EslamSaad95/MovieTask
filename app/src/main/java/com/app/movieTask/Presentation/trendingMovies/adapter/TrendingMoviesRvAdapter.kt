package com.app.movieTask.Presentation.trendingMovies.adapter

import com.app.movieTask.Presentation.utils.base.BaseAdapter
import com.app.movieTask.databinding.ItemTrendingMoviesBinding
import com.app.movieTask.domain.entity.TrendingMoviesEntity

class TrendingMoviesRvAdapter:BaseAdapter<ItemTrendingMoviesBinding,TrendingMoviesEntity>() {

  override fun setContent(binding: ItemTrendingMoviesBinding, item: TrendingMoviesEntity, position: Int) {
    binding.apply {
      root.setOnClickListener { onViewClicked(it,item, position) }
      movie=item
    }
  }
}