package com.santrucho.argencine.ui

import android.os.Parcelable
import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id") val id : String?,
    @SerializedName("overview") val description : String?,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val poster : String?,
) : Parcelable {
    constructor() : this("","","","")
}