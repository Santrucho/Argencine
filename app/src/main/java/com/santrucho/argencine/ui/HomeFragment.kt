package com.santrucho.argencine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.santrucho.argencine.R
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.databinding.FragmentHomeBinding
import com.santrucho.argencine.domain.DataSource
import com.santrucho.argencine.domain.repo.MovieRepositoryImpl
import com.santrucho.argencine.ui.viewmodel.MainViewModel
import com.santrucho.argencine.ui.viewmodel.VMFactory
import com.santrucho.argencine.vo.Resource
import java.util.*

class HomeFragment : Fragment(), MoviesAdapter.onMovieClickListener {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, VMFactory(MovieRepositoryImpl(DataSource()))
        ).get(
            MainViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        viewModel.fetchMoviesList.observe(viewLifecycleOwner, Observer{ result ->
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.adapter = MoviesAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurrio un error al obtener las peliculas", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }

    override fun onMovieClick(movie: Movie) {
        findNavController().navigate(R.id.detailFragment)
    }
}