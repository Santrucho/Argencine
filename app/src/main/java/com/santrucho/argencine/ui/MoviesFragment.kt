package com.santrucho.argencine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.santrucho.argencine.databinding.FragmentMoviesBinding
import com.santrucho.argencine.model.RepoImpl
import com.santrucho.argencine.viewmodel.MainViewModel
import com.santrucho.argencine.vo.DataSource
import com.santrucho.argencine.vo.Resource
import androidx.lifecycle.Observer

class MoviesFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private var _binding : FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        getMovieData()
    }

    private fun getMovieData() {
        viewModel.fetchMoviesList.observe(
            viewLifecycleOwner
        ) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovies.adapter = MoviesAdapter(requireContext(), result.data)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    showError()
                }
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }


    private fun showError(){
        Toast.makeText(requireContext(),"Ha ocurrido un error", Toast.LENGTH_LONG).show()
    }
}