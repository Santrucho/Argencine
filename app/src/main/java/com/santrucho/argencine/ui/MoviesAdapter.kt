package com.santrucho.argencine.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santrucho.argencine.R
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.databinding.ItemMovieBinding

class MoviesAdapter(private val context: Context, private var movies : List<Movie>,private val itemClickListener : onMovieClickListener) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    interface onMovieClickListener{
        fun onMovieClick(movie:Movie)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }
    override fun getItemCount(): Int = movies.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(movieList:List<Movie>){
        this.movies = movieList
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val binding = ItemMovieBinding.bind(itemView)
        private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

        fun bind(movie: Movie){
            binding.movieTitle.text = movie.title
            binding.movieDescription.text = movie.description
            Glide.with(itemView).load(IMAGE_URL + movie.poster).into(binding.moviePoster)
            itemView.setOnClickListener { itemClickListener.onMovieClick(movie) }
        }
    }
}