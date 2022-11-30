package com.example.binarwatchflix.data.network.api


import com.example.binarwatchflix.base.BaseRepository
import com.example.binarwatchflix.data.network.api.datasource.TmdbApiDataSource
import com.example.binarwatchflix.data.network.api.response.cast.CastResponse
import com.example.binarwatchflix.data.network.api.response.movie.MovieDetailResponse
import com.example.binarwatchflix.data.network.api.response.movie.MoviesResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowDetailResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowsResponse
import com.example.binarwatchflix.wrapper.Resource

interface Repository {
    suspend fun getMovieNowPlaying() : Resource<MoviesResponse>
    suspend fun getMoviePopular() : Resource<MoviesResponse>
    suspend fun getMovieUpcoming() : Resource<MoviesResponse>

    suspend fun getTvShowOnTheAir() : Resource<TvShowsResponse>
    suspend fun getTvShowTopRated() : Resource<TvShowsResponse>
    suspend fun getTvShowPopular() : Resource<TvShowsResponse>
    suspend fun getMovieDetail(movieId: String) : Resource<MovieDetailResponse>
    suspend fun getTvShowDetail(tvShowId: String) : Resource<TvShowDetailResponse>

    suspend fun getMovieCast(movieId: String) : Resource<CastResponse>
    suspend fun getTvShowCast(tvShowId: String) : Resource<CastResponse>
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
    override suspend fun getTvShowDetail(tvShowId: String): Resource<TvShowDetailResponse> {
        return doNetworkCall { networkDataSource.getTvShowDetail(tvShowId) }
    }

    override suspend fun getMovieCast(movieId: String): Resource<CastResponse> {
        return doNetworkCall { networkDataSource.getMovieCast(movieId) }
    }

    override suspend fun getTvShowCast(tvShowId: String): Resource<CastResponse> {
        return doNetworkCall { networkDataSource.getTvShowCast(tvShowId) }
    }
    override suspend fun getMovieDetail(movieId: String): Resource<MovieDetailResponse> {
        return doNetworkCall { networkDataSource.getMovieDetail(movieId) }
    }

}