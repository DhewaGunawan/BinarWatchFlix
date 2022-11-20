package com.example.binarwatchflix.pkg.home.ui.homelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binarwatchflix.R
import com.example.binarwatchflix.data.model.Tmdb
import com.example.binarwatchflix.data.network.api.Repository
import com.example.binarwatchflix.data.network.api.response.movie.MoviesResponse
import com.example.binarwatchflix.pkg.home.ui.uimodel.HomeItem
import com.example.binarwatchflix.wrapper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeListViewModel(private val repository: Repository) : ViewModel() {

    val movieDataResult = MutableLiveData<Resource<List<HomeItem>>>()


    fun getMovieData() {
        viewModelScope.launch(Dispatchers.IO) {
            movieDataResult.postValue(Resource.Loading())
            val nowPlayingMovie = repository.getMovieNowPlaying()
            val popularMovie = repository.getMoviePopular()
            val upComingMovie = repository.getMovieUpcoming()
            val homeItems = mutableListOf<HomeItem>()
            homeItems.apply {
                nowPlayingMovie.payload?.let {
                    add(HomeItem.HomeHeaderMovieItem(it.results.random()))
                }

                nowPlayingMovie.payload?.let {
                    add(HomeItem.HomeSectionMovieItem(R.string.now_playing_movie, it.results))
                }

                popularMovie.payload?.let {
                    add(HomeItem.HomeSectionMovieItem(R.string.popular_movie, it.results))
                }

                upComingMovie.payload?.let {
                    add(HomeItem.HomeSectionMovieItem(R.string.upcoming_movie, it.results))
                }
            }
            if (homeItems.isNotEmpty()) {
                movieDataResult.postValue(Resource.Success(homeItems))
            } else {
                movieDataResult.postValue(Resource.Empty())
            }
        }
    }


}