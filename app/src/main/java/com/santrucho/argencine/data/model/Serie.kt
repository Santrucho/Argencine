package com.santrucho.argencine.data.model

import com.google.gson.annotations.SerializedName

data class Serie(
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("overview")
    val description : String = "",
    @SerializedName("name")
    val title: String = "",
    @SerializedName("poster_path")
    val poster : String = "",
    @SerializedName("first_air_date")
    val release_date : String = "",
    @SerializedName("original_language")
    val original_language : String = "",
    @SerializedName("popularity")
    val popularity : Number,
    @SerializedName("genres")
    val genres : List<Genre> = emptyList(),
    @SerializedName("number_of_seasons")
    val seasons_number : Int = 0,
    @SerializedName("number_of_episodes")
    val episodes_number : Int = 0

)
