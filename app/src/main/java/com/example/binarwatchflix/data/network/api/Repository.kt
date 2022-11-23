package com.example.binarwatchflix.data.network.api


import com.example.binarwatchflix.base.BaseRepository
import com.example.binarwatchflix.data.network.api.datasource.TmdbApiDataSource
import com.example.binarwatchflix.data.network.api.response.movie.MoviesResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowsResponse
import com.example.binarwatchflix.wrapper.Resource

interface Repository {
    suspend fun getMovieNowPlaying() : Resource<MoviesResponse>
    suspend fun getMoviePopular() : Resource<MoviesResponse>
    suspend fun getMovieUpcoming() : Resource<MoviesResponse>

    suspend fun getTvShowOnTheAir() : Resource<TvShowsResponse>
    suspend fun getTvShowTopRated() : Resource<TvShowsResponse>
    suspend fun getTvShowPopular() : Resource<TvShowsResponse>
}

class RepositoryImpl(private val networkDataSource: TmdbApiDataSource) : Repository, BaseRepository() {
    override suspend fun getMovieNowPlaying(): Resource<MoviesResponse> {
        return doNetworkCall { networkDataSource.getMovieNowPlaying() }
    }

    override suspend fun getMoviePopular(): Resource<MoviesResponse> {
        return doNetworkCall { networkDataSource.getMoviePopular() }
    }

    override suspend fun getMovieUpcoming(): Resource<MoviesResponse> {
        return doNetworkCall { networkDataSource.getMovieUpcoming() }
    }

    override suspend fun getTvShowOnTheAir(): Resource<TvShowsResponse> {
        return doNetworkCall { networkDataSource.getTvShowOnTheAir() }
    }

    override suspend fun getTvShowTopRated(): Resource<TvShowsResponse> {
        return doNetworkCall { networkDataSource.getTvShowTopRated() }
    }

    override suspend fun getTvShowPopular(): Resource<TvShowsResponse> {
        return doNetworkCall { networkDataSource.getTvShowPopular() }
    }

}