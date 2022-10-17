package com.santrucho.argencine.domain

import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.vo.Resource
import com.santrucho.argencine.vo.RetrofitClient

class DataSource {

    suspend fun getPopularMovies() : Resource<List<Movie>> {
       return Resource.Success(RetrofitClient.webservice.getPopularMovies().moviesList)
    }
}