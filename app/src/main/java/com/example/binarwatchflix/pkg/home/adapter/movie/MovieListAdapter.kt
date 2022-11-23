package com.example.binarwatchflix.pkg.home.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.binarwatchflix.data.network.api.response.movie.MovieList
import com.example.binarwatchflix.databinding.ItemFilmPosterBinding
import com.example.binarwatchflix.databinding.ItemFilmPosterGridBinding


class MovieListAdapter(
    private val isGridLayout: Boolean = false,
    private val itemClick: (MovieList) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<MovieList> = mutableListOf()

    fun setItems(items: List<MovieList>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (!isGridLayout) {
            PosterViewHolderImpl(
                ItemFilmPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                itemClick
            )
        } else {
            GridPosterViewHolderImpl(
                ItemFilmPosterGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                itemClick
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MoviePosterViewHolder) {
            holder.bindView(items[position])
        }
    }

    override fun getItemCount(): Int = items.size


}