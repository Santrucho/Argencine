package com.santrucho.argencine.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.Serie
import com.santrucho.argencine.databinding.FragmentDetailBinding
import com.santrucho.argencine.ui.viewmodel.MainViewModel
import com.santrucho.argencine.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSerieFragment : Fragment() {

    private val args : DetailSerieFragmentArgs by navArgs()

    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    private val viewModel : MainViewModel by viewModels()

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        val serieId = args.serieId
        viewModel.serieDetail(serieId = serieId)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.serieDetail.observe(viewLifecycleOwner, Observer{ result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    hideTitle(true)
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    hideTitle(false)
                    showUI(result.data)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"No se cargo correctamente", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun hideTitle(show : Boolean){
        if(show){
            binding.origLanguageTitle.visibility = View.GONE
            binding.releasteDateTitle.visibility = View.GONE
            binding.popularityTitle.visibility = View.GONE
        } else{
            binding.origLanguageTitle.visibility = View.VISIBLE
            binding.releasteDateTitle.visibility = View.VISIBLE
            binding.popularityTitle.visibility = View.VISIBLE
        }
    }

    private fun showUI(serie: Serie) {
        Glide.with(requireContext()).load(IMAGE_URL + serie.poster).into(binding.poster)
        binding.title.text = serie.title
        binding.overview.text = serie.description
        binding.originalLanguage.text = serie.original_language
        binding.releasteDate.text = serie.release_date
        binding.popularity.text = serie.popularity.toString()

        val chipGroupGenres = binding.chipGroupGenres

        chipGroupGenres.removeAllViews()

        serie.genres.forEach { genre ->
            val chip = Chip(requireContext())
            chip.text = genre.name
            chip.isClickable = true
            chipGroupGenres.addView(chip)
        }
    }
}