package com.santrucho.argencine.domain.repo


import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.domain.DataSource
import com.santrucho.argencine.vo.Resource

class MovieRepositoryImpl(private val dataSource : DataSource) : MovieRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return dataSource.getPopularMovies()
    }

    override suspend fun getMovieDetails(movieId:Long): Resource<Movie> {
        return dataSource.getMovieDetails(movieId)
    }
}