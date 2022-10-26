package com.santrucho.argencine.data.network

import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {

    @GET("popular?api_key=dde7c62f4988449a75b1afbb7200ae2c&language=en-US&page=1")
    suspend fun getPopularMovies() : MovieResponse

    @GET("{movie_id}?api_key=dde7c62f4988449a75b1afbb7200ae2c&language=en-US&page=1")
    suspend fun getMovieDetails(@Query("movie_id") movie_id : Long) : Movie

}