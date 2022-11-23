package com.example.binarwatchflix.pkg.home.adapter.movie

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.binarwatchflix.constant.CommonConstant
import com.example.binarwatchflix.data.network.api.response.movie.MovieList

import com.example.binarwatchflix.databinding.ItemFilmPosterBinding
import com.example.binarwatchflix.databinding.ItemFilmPosterGridBinding

interface MoviePosterViewHolder {
    fun bindView(item: MovieList?)
}

class PosterViewHolderImpl(
    private val binding: ItemFilmPosterBinding,
    val itemClick: (MovieList) -> Unit
) : RecyclerView.ViewHolder(binding.root), MoviePosterViewHolder {

    override fun bindView(item: MovieList?) {
        item?.let { movie ->
            binding.ivPoster.load(CommonConstant.URL_IMAGE + movie.posterPath)
            itemView.setOnClickListener { itemClick(movie) }
        }
    }
}

class GridPosterViewHolderImpl(
    private val binding: ItemFilmPosterGridBinding,
    val itemClick: (MovieList) -> Unit
) : RecyclerView.ViewHolder(binding.root), MoviePosterViewHolder {

    override fun bindView(item: MovieList?) {
        item?.let { movie ->
            binding.ivPoster.load(CommonConstant.URL_IMAGE + movie.posterPath)
            itemView.setOnClickListener { itemClick(movie) }
        }
    }
}