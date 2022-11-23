package com.example.binarwatchflix.pkg.home.adapter.tvshow

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.binarwatchflix.constant.CommonConstant
import com.example.binarwatchflix.data.network.api.response.movie.MovieList
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowList

import com.example.binarwatchflix.databinding.ItemFilmPosterBinding
import com.example.binarwatchflix.databinding.ItemFilmPosterGridBinding

interface PosterViewHolder {
    fun bindView(item: TvShowList?)
}

class PosterViewHolderImpl(
    private val binding: ItemFilmPosterBinding,
    val itemClick: (TvShowList) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {

    override fun bindView(item: TvShowList?) {
        item?.let { tvShow ->
            binding.ivPoster.load(CommonConstant.URL_IMAGE + tvShow.posterPath)
            itemView.setOnClickListener { itemClick(tvShow) }
        }
    }
}

class GridPosterViewHolderImpl(
    private val binding: ItemFilmPosterGridBinding,
    val itemClick: (TvShowList) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {

    override fun bindView(item: TvShowList?) {
        item?.let { tvShow ->
            binding.ivPoster.load(CommonConstant.URL_IMAGE + tvShow.posterPath)
            itemView.setOnClickListener { itemClick(tvShow) }
        }
    }
}