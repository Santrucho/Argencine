package com.santrucho.argencine.domain

import android.util.Log
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.Serie
import com.santrucho.argencine.data.network.WebService
import com.santrucho.argencine.vo.Resource
import javax.inject.Inject

class DataSource @Inject constructor(private val webService: WebService){

    suspend fun getPopularMovies() : Resource<List<Movie>> {
       return Resource.Success(webService.getPopularMovies().moviesList)
    }

    suspend fun getMovieDetails(movieId : Int) : Resource<Movie>{
        return Resource.Success(webService.getMovieDetails(movieId))
    }

    suspend fun getLatestMovies() : Resource<List<Movie>>{
        return Resource.Success(webService.getLatestMovies().moviesList)
    }

    suspend fun getTopSeries() : Resource<List<Serie>>{
        return Resource.Success(webService.getTopSeries().series)
    }

    suspend fun getSerieDetails(seriesId:Int) : Resource<Serie>{
        return Resource.Success(webService.getSerieDetail(seriesId))
    }
}