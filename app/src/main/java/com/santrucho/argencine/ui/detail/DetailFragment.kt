package com.santrucho.argencine.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        requireArguments().let{
            movie = it.getParcelable<Movie>("movie")!!
        }
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(IMAGE_URL + movie.poster).into(binding.poster)
        binding.title.text = movie.title
        binding.overview.text = movie.description
        binding.originalLanguage.text = movie.original_language
        binding.releasteDate.text = movie.release_date
        binding.popularity.text = movie.popularity.toString()
    }
}