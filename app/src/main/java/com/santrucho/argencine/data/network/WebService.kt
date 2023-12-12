package com.santrucho.argencine.data.network

import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.MovieResponse
import com.santrucho.argencine.data.model.Serie
import com.santrucho.argencine.data.model.SerieResponse
import com.santrucho.argencine.vo.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WebService {

    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularMovies() : MovieResponse

    @GET("movie/{movie_id}?api_key=$API_KEY&language=en")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int) : Movie

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getLatestMovies() : MovieResponse

    @GET("tv/top_rated?api_key=$API_KEY&language=en")
    suspend fun getTopSeries() : SerieResponse

    @GET("tv/{series_id}?api_key=$API_KEY&language=en")
    suspend fun getSerieDetail(@Path("series_id") seriesId : Int) : Serie

}