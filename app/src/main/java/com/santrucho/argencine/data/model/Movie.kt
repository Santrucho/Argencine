package com.santrucho.argencine.data.model

import android.os.Parcelable
import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("overview")
    val description : String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("poster_path")
    val poster : String = "",
    @SerializedName("release_date")
    val release_date : String = "",
    @SerializedName("original_language")
    val original_language : String = "",
    @SerializedName("popularity")
    val popularity : Number
) : Parcelable
