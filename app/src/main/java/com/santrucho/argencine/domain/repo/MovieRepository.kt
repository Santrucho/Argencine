package com.santrucho.argencine.domain.repo

import com.santrucho.argencine.vo.Resource
import com.santrucho.argencine.data.model.Movie


interface MovieRepository {
    suspend fun getPopularMovies() : Resource<List<Movie>>

    suspend fun getMovieDetails(movieId:Long) : Resource<Movie>
}