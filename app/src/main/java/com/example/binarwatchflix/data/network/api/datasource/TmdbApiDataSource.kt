package com.example.binarwatchflix.data.network.api.datasource

import com.example.binarwatchflix.data.network.api.response.movie.MoviesResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowsResponse
import com.example.binarwatchflix.data.network.api.service.TmdbApiService


interface TmdbApiDataSource {

    suspend fun getMovieNowPlaying(): MoviesResponse
    suspend fun getMoviePopular(): MoviesResponse
    suspend fun getMovieUpcoming(): MoviesResponse

    suspend fun getTvShowOnTheAir(): TvShowsResponse
    suspend fun getTvShowTopRated(): TvShowsResponse
    suspend fun getTvShowPopular(): TvShowsResponse


}

class TmdbApiDataSourceImpl(private val service: TmdbApiService) : TmdbApiDataSource {
    override suspend fun getMovieNowPlaying(): MoviesResponse {
        return service.getMovieNowPlaying()
    }

    override suspend fun getMoviePopular(): MoviesResponse {
        return service.getMoviePopular()
    }

    override suspend fun getMovieUpcoming(): MoviesResponse {
        return service.getMovieUpcoming()
    }

    override suspend fun getTvShowOnTheAir(): TvShowsResponse {
        return service.getTvShowOnTheAir()
    }

    override suspend fun getTvShowTopRated(): TvShowsResponse {
        return service.getTvShowTopRated()
    }

    override suspend fun getTvShowPopular(): TvShowsResponse {
        return service.getTvShowPopular()
    }

}