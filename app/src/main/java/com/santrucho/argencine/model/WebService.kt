package com.santrucho.argencine.model

import com.santrucho.argencine.ui.Movie
import com.santrucho.argencine.ui.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WebService {

    @GET("/3/popular/movie?api_key=dde7c62f4988449a75b1afbb7200ae2c")
    suspend fun getMovieByName(@Query("movieName") movieName:String) : MovieResponse

}