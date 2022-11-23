package com.example.binarwatchflix.data.network.api.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.binarwatchflix.BuildConfig
import com.example.binarwatchflix.data.network.api.response.movie.MoviesResponse
import com.example.binarwatchflix.data.network.api.response.tvshow.TvShowsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface TmdbApiService {

    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): MoviesResponse

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): MoviesResponse

    @GET("tv/on_the_air")
    suspend fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): TvShowsResponse

    @GET("tv/top_rated")
    suspend fun getTvShowTopRated(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): TvShowsResponse

    @GET("tv/popular")
    suspend fun getTvShowPopular(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): TvShowsResponse


    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): TmdbApiService {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()

            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL_TMDB)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

            return retrofit.create(TmdbApiService::class.java)
        }
    }
}