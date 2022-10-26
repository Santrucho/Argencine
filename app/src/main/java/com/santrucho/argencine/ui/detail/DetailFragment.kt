package com.santrucho.argencine.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.databinding.FragmentDetailBinding
import com.santrucho.argencine.domain.DataSource
import com.santrucho.argencine.domain.repo.MovieRepositoryImpl
import com.santrucho.argencine.ui.MoviesAdapter
import com.santrucho.argencine.ui.viewmodel.MainViewModel
import com.santrucho.argencine.ui.viewmodel.VMFactory
import com.santrucho.argencine.vo.Resource

class DetailFragment : Fragment() {

    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, VMFactory(MovieRepositoryImpl(DataSource()))
        ).get(
            MainViewModel::class.java
        )
    }

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieDetail.observe(viewLifecycleOwner,Observer{ result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showUI(result.data)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"No se cargo correctamente", Toast.LENGTH_SHORT).show()
                }
            }

        })


    }

    private fun showUI(movie: Movie) {
        Glide.with(requireContext()).load(IMAGE_URL + movie.poster).into(binding.poster)
        binding.title.text = movie.title
        binding.overview.text = movie.description
        binding.originalLanguage.text = movie.original_language
        binding.releasteDate.text = movie.release_date
        binding.popularity.text = movie.popularity.toString()
    }
}