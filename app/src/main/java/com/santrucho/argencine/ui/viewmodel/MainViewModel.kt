package com.santrucho.argencine.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.santrucho.argencine.domain.repo.MovieRepository
import com.santrucho.argencine.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val fetchMoviesList = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            val movieList = movieRepository.getPopularMovies()
            emit(movieList)
        }
        catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    val movieDetail = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            val movie = movieRepository.getMovieDetails(18)
            emit(movie)
        }
        catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}