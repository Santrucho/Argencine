package com.santrucho.argencine.vo

import com.santrucho.argencine.model.MovieAPIService
import com.santrucho.argencine.model.WebService
import com.santrucho.argencine.ui.Movie

class DataSource {

    suspend fun getMovieByName(movieName : String) : Resource<List<Movie>>{
       return Resource.Success(MovieAPIService.webservice.getMovieByName(movieName).moviesList)
    }
}