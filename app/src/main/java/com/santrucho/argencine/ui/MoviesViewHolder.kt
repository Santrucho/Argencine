package com.santrucho.argencine.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santrucho.argencine.R
import com.santrucho.argencine.ui.Movie

class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    private val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
    private val movieDescription = itemView.findViewById<TextView>(R.id.movieDescription)
    private val imageMovie: ImageView = itemView.findViewById(R.id.moviePoster)

    fun bindMovie(movie: Movie){
        movieTitle.text = movie.title
        movieDescription.text = movie.description
        Glide.with(itemView).load(IMAGE_URL + movie.poster).into(imageMovie)
    }
}