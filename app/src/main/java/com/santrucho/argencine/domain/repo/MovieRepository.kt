package com.santrucho.argencine.domain.repo

import com.santrucho.argencine.vo.Resource
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.Serie


interface MovieRepository {
    suspend fun getPopularMovies() : Resource<List<Movie>>

    suspend fun getMovieDetails(movieId:Int) : Resource<Movie>

    suspend fun getLatestMovies() : Resource<List<Movie>>

    suspend fun getTopSeries() : Resource<List<Serie>>

    suspend fun getSeriesDetail(seriesId:Int) : Resource<Serie>
}