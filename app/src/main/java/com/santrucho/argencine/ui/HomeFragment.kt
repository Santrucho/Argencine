package com.santrucho.argencine.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.Serie
import com.santrucho.argencine.databinding.FragmentHomeBinding
import com.santrucho.argencine.domain.DataSource
import com.santrucho.argencine.domain.repo.MovieRepositoryImpl
import com.santrucho.argencine.ui.viewmodel.MainViewModel
import com.santrucho.argencine.ui.viewmodel.VMFactory
import com.santrucho.argencine.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), MoviesAdapter.onMovieClickListener, SeriesAdapter.onSerieClickListener {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.popularMovies()
        viewModel.latestMovies()
        viewModel.popularSeries()

        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPopularMovies()
        setupLatestMovies()
        setupPopularSeries()

    }

    private fun setupPopularMovies(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewPopular.layoutManager = layoutManager

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer{ result ->
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerViewPopular.adapter = MoviesAdapter(requireContext(),result.data.take(6),this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurrio un error al obtener las peliculas", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupLatestMovies(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewLatest.layoutManager = layoutManager

        viewModel.latestState.observe(viewLifecycleOwner, Observer{ result ->
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerViewLatest.adapter = MoviesAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurrio un error al obtener las peliculas", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupPopularSeries(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSeries.layoutManager = layoutManager

        viewModel.topRatedSerie.observe(viewLifecycleOwner, Observer{ result ->
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerViewSeries.adapter = SeriesAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurrio un error al obtener las series", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    override fun onMovieClick(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }

    override fun onSerieClick(serie: Serie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailSerieFragment(serie.id)
        findNavController().navigate(action)
    }
}