package com.santrucho.argencine.ui

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    @SerializedName("results")
    val moviesList : List<Movie>)
