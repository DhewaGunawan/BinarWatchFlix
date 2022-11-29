package com.example.binarwatchflix.pkg.detail.ui

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binarwatchflix.data.network.api.Repository
import com.example.binarwatchflix.data.network.api.response.cast.CastResponse
import com.example.binarwatchflix.data.network.api.response.movie.MovieDetailResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowDetailResponse
import com.example.binarwatchflix.wrapper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository, val intentData: Bundle) : ViewModel() {
    val movieDetailResult = MutableLiveData<Resource<MovieDetailResponse>>()
    val tvShowDetailResult = MutableLiveData<Resource<TvShowDetailResponse>>()
    val castResult = MutableLiveData<Resource<CastResponse>>()

    fun getTypeDetail() = intentData.getString(DetailMovie.EXTRAS_TYPE)

    fun loadMovieDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailResult.postValue(Resource.Loading())
            val movieId = intentData.getString(DetailMovie.EXTRAS_ID)
            if (movieId.isNullOrEmpty()){
                movieDetailResult.postValue(
                    Resource.Empty()
                )
            } else {
                val movieDetail = repository.getMovieDetail(movieId)
                movieDetail.payload?.let {
                    movieDetailResult.postValue(
                        Resource.Success(it)
                    )
                }
            }
        }
    }

    fun loadTvShowDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowDetailResult.postValue(Resource.Loading())
            val tvShowId = intentData.getString(DetailMovie .EXTRAS_ID)
            if (tvShowId.isNullOrEmpty()){
                tvShowDetailResult.postValue(
                    Resource.Empty()
                )
            } else {
                val tvShowDetail = repository.getTvShowDetail(tvShowId)
                tvShowDetail.payload?.let {
                    tvShowDetailResult.postValue(
                        Resource.Success(it)
                    )
                }
            }
        }
    }

    fun loadCastMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            castResult.postValue(Resource.Loading())
            val id = intentData.getString(DetailMovie.EXTRAS_ID)
            if (id.isNullOrEmpty()){
                castResult.postValue(Resource.Empty())
            } else {
                val castDetail = repository.getMovieCast(id)
                castDetail.payload?.let {
                    castResult.postValue(Resource.Success(it))
                }
            }
        }
    }

    fun loadCastTvShow() {
        viewModelScope.launch(Dispatchers.IO) {
            castResult.postValue(Resource.Loading())
            val id = intentData.getString(DetailMovie.EXTRAS_ID)
            if (id.isNullOrEmpty()){
                castResult.postValue(Resource.Empty())
            } else {
                val castDetail = repository.getTvShowCast(id)
                castDetail.payload?.let {
                    castResult.postValue(Resource.Success(it))
                }
            }
        }
    }


}