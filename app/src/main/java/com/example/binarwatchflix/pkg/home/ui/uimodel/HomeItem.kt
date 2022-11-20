package com.example.binarwatchflix.pkg.home.ui.uimodel

import androidx.annotation.StringRes
import com.example.binarwatchflix.data.network.api.response.movie.MovieList
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowList


const val HOME_TYPE_HEADER = 0
const val HOME_TYPE_SECTION = 1


sealed class HomeItem(val type: Int) {
    class HomeHeaderMovieItem(val data: MovieList) : HomeItem(HOME_TYPE_HEADER)
    class HomeSectionMovieItem(@StringRes val sectionName: Int, val data: List<MovieList>) :
        HomeItem(HOME_TYPE_SECTION)

    class HomeHeaderTvShowItem(val data: TvShowList) : HomeItem(HOME_TYPE_HEADER)
    class HomeSectionTvShowItem(@StringRes val sectionName: Int, val data: List<TvShowList>) :
        HomeItem(HOME_TYPE_SECTION)
}
