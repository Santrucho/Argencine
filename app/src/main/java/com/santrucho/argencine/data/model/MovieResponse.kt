package com.santrucho.argencine.data.model

import com.google.gson.annotations.SerializedName
import com.santrucho.argencine.data.model.Movie

data class MovieResponse(
    @SerializedName("results")
    val moviesList : List<Movie> )
