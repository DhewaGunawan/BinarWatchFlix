package com.example.binarwatchflix.pkg.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binarwatchflix.data.network.api.response.genre.GenreResponse
import com.example.binarwatchflix.databinding.ItemGenreBinding

class GenreAdapter() : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private var genreList: MutableList<GenreResponse> = mutableListOf()

    class GenreViewHolder(private val binding: ItemGenreBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: GenreResponse) {
            binding.tvDetailGenre.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GenreAdapter.GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindView(genreList[position])
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    fun setData(list : List<GenreResponse>){
        genreList.clear()
        genreList.addAll(list)
        notifyDataSetChanged()
    }
}