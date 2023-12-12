package com.santrucho.argencine.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santrucho.argencine.R
import com.santrucho.argencine.data.model.Serie
import com.santrucho.argencine.databinding.ItemSeriePopularBinding

class SeriesAdapter(
    private val context: Context,
    private var series: List<Serie>,
    private val itemClickListener: onSerieClickListener
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    interface onSerieClickListener {
        fun onSerieClick(serie: Serie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_serie_popular, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int = series.size


    inner class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemSeriePopularBinding.bind(itemView)
        private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

        fun bind(serie: Serie) {
            binding.serieTitle.text = serie.title
            Glide.with(itemView).load(IMAGE_URL + serie.poster)
                .into(binding.seriePosterPopular).view.scaleType
            itemView.setOnClickListener { itemClickListener.onSerieClick(serie) }
        }
    }
}