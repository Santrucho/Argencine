package com.santrucho.argencine.model

import com.santrucho.argencine.vo.Resource
import com.santrucho.argencine.ui.Movie


interface Repo {
    suspend fun getMovieList(movieName: String) : Resource<List<Movie>>
}