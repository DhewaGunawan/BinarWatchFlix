package com.example.binarwatchflix.pkg.home.adapter.tvshow

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.binarwatchflix.constant.CommonConstant
import com.example.binarwatchflix.databinding.ItemHeaderFilmBinding
import com.example.binarwatchflix.databinding.ItemSectionFilmBinding
import com.example.binarwatchflix.pkg.home.ui.uimodel.HOME_TYPE_HEADER
import com.example.binarwatchflix.pkg.home.ui.uimodel.HomeItem
import com.example.binarwatchflix.utils.Converter.splitYear

class TvShowAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val data = mutableListOf<HomeItem>()

    fun setItems(newData: List<HomeItem>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HOME_TYPE_HEADER) {
            HomeHeaderItemViewHolder(
                ItemHeaderFilmBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            HomeSectionItemViewHolder(
                ItemSectionFilmBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeHeaderItemViewHolder)
            holder.bind(data[position] as HomeItem.HomeHeaderTvShowItem)
        else if (holder is HomeSectionItemViewHolder)
            holder.bind(data[position] as HomeItem.HomeSectionTvShowItem)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

}


class HomeHeaderItemViewHolder(private val binding: ItemHeaderFilmBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeItem.HomeHeaderTvShowItem) {
        binding.ivHeader.load(CommonConstant.URL_IMAGE + item.data.posterPath)
        binding.tvTitle.text = item.data.tvShowTitle
        binding.tvYear.text = splitYear(item.data.tvFirstAirDate)
    }

}

class HomeSectionItemViewHolder(private val binding: ItemSectionFilmBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val adapter: TvShowListAdapter by lazy {
        TvShowListAdapter {
           // AnimeDetailActivity.startActivity(itemView.context, it.animeId)
        }
    }

    fun bind(item: HomeItem.HomeSectionTvShowItem) {
        Log.d("TAG2", item.data.toString())
        binding.tvTitleSection.text = itemView.context.getText(item.sectionName)
        binding.rvContents.adapter = adapter
        adapter.setItems(item.data)
    }

}