package com.santrucho.argencine.data.network

import com.santrucho.argencine.data.model.MovieResponse
import retrofit2.http.GET


interface WebService {

    @GET("popular?api_key=dde7c62f4988449a75b1afbb7200ae2c&language=en-US&page=1")
    suspend fun getPopularMovies() : MovieResponse

}