package com.example.binarwatchflix.pkg.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.binarwatchflix.R
import com.example.binarwatchflix.constant.CommonConstant
import com.example.binarwatchflix.data.network.api.response.cast.CastItem
import com.example.binarwatchflix.databinding.ItemCastPosterBinding

class CastAdapter(var itemClick:( (CastItem) -> Unit)? = null) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var castList: MutableList<CastItem> = mutableListOf()

    class CastViewHolder(
        private val binding: ItemCastPosterBinding,
        val itemClick: ((CastItem) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: CastItem) {
            with(item) {
                itemClick?.let {
                    itemView.setOnClickListener {
                        it(this)
                    }
                }
            }

            if (item.profilePath.isNullOrEmpty()){
                binding.ivCastPoster.setImageResource(R.drawable.ic_placeholder_user)
            } else {
                binding.ivCastPoster.load(CommonConstant.URL_IMAGE + item.profilePath){
                    placeholder(R.drawable.ic_placeholder_user)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding =  ItemCastPosterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CastViewHolder(binding,itemClick)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindView(castList[position])
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    fun setData(list : List<CastItem>){
        castList.clear()
        castList.addAll(list)
        notifyDataSetChanged()
    }
}