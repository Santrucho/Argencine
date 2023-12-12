package com.santrucho.argencine.domain.repo


import android.util.Log
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.Serie
import com.santrucho.argencine.domain.DataSource
import com.santrucho.argencine.vo.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource : DataSource) : MovieRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return dataSource.getPopularMovies()
    }

    override suspend fun getMovieDetails(movieId:Int): Resource<Movie> {
        return dataSource.getMovieDetails(movieId)
    }

    override suspend fun getLatestMovies(): Resource<List<Movie>> {
        return dataSource.getLatestMovies()
    }

    override suspend fun getTopSeries(): Resource<List<Serie>> {
        return dataSource.getTopSeries()
    }

    override suspend fun getSeriesDetail(seriesId: Int): Resource<Serie> {
        return dataSource.getSerieDetails(seriesId)
    }
}