package com.example.binarwatchflix.data.network.api.datasource

import com.example.binarwatchflix.data.network.api.response.cast.CastResponse
import com.example.binarwatchflix.data.network.api.response.movie.MovieDetailResponse
import com.example.binarwatchflix.data.network.api.response.movie.MoviesResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowDetailResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowsResponse
import com.example.binarwatchflix.data.network.api.service.TmdbApiService


interface TmdbApiDataSource {

    suspend fun getMovieNowPlaying(): MoviesResponse
    suspend fun getMoviePopular(): MoviesResponse
    suspend fun getMovieUpcoming(): MoviesResponse

    suspend fun getTvShowOnTheAir(): TvShowsResponse
    suspend fun getTvShowTopRated(): TvShowsResponse
    suspend fun getTvShowPopular(): TvShowsResponse

    //getDetail
    suspend fun getMovieDetail(movieId: String): MovieDetailResponse
    suspend fun getTvShowDetail(tvShowId: String): TvShowDetailResponse
    //getCast
    suspend fun getMovieCast(movieId: String) : CastResponse
    suspend fun getTvShowCast(tvShowId: String) : CastResponse
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
    override suspend fun getMovieDetail(movieId: String): MovieDetailResponse {
        return service.getDetailMovie(movieId)
    }

    override suspend fun getTvShowDetail(tvShowId: String): TvShowDetailResponse {
        return service.getDetailTvShow(tvShowId)
    }

    override suspend fun getMovieCast(movieId: String): CastResponse {
        return service.getMovieCast(movieId)
    }

    override suspend fun getTvShowCast(tvShowId: String): CastResponse {
        return service.getTvShowCast(tvShowId)}

}