package com.santrucho.argencine.model


import com.santrucho.argencine.ui.Movie
import com.santrucho.argencine.vo.DataSource
import com.santrucho.argencine.vo.Resource

class RepoImpl(private val dataSource : DataSource) : Repo {

    override suspend fun getMovieList(movieName: String): Resource<List<Movie>> {
        return dataSource.getMovieByName(movieName)
    }
}