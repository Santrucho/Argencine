package com.santrucho.argencine.data.model

import com.google.gson.annotations.SerializedName

data class SerieResponse(
    @SerializedName("results")
    val series : List<Serie>
) {
}