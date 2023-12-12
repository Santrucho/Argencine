package com.santrucho.argencine.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santrucho.argencine.data.model.Movie
import com.santrucho.argencine.data.model.Serie
import com.santrucho.argencine.domain.repo.MovieRepository
import com.santrucho.argencine.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    private val _movieDetail = MutableLiveData<Resource<Movie>>()
    val movieDetail: LiveData<Resource<Movie>>
        get() = _movieDetail

    private val _serieDetail = MutableLiveData<Resource<Serie>>()
    val serieDetail : LiveData<Resource<Serie>>
        get() = _serieDetail

    private val _topRatedSeries = MutableLiveData<Resource<List<Serie>>>()
    val topRatedSerie: LiveData<Resource<List<Serie>>>
        get() = _topRatedSeries

    private val _popularMovies = MutableLiveData<Resource<List<Movie>>>()
    val popularMovies: LiveData<Resource<List<Movie>>>
        get() = _popularMovies

    private val _latestMovies = MutableLiveData<Resource<List<Movie>>>()
    val latestState: LiveData<Resource<List<Movie>>>
        get() = _latestMovies

    fun popularMovies(){
        viewModelScope.launch {
            _popularMovies.value = Resource.Loading()
            try{
                val movies = movieRepository.getPopularMovies()
                _popularMovies.value = movies
            }catch (e:Exception){
                _popularMovies.value = Resource.Failure(e)
            }
        }
    }

    fun latestMovies(){
        viewModelScope.launch {
            _latestMovies.value = Resource.Loading()
            try{
                val movies = movieRepository.getLatestMovies()
                _latestMovies.value = movies
            }catch (e:Exception){
                _latestMovies.value = Resource.Failure(e)
            }
        }
    }

    fun popularSeries(){
        viewModelScope.launch {
            _topRatedSeries.value = Resource.Loading()
            try{
                val series = movieRepository.getTopSeries()
                _topRatedSeries.value = series
            }catch (e:Exception){
                _topRatedSeries.value = Resource.Failure(e)
            }
        }
    }

    fun movieDetail(movieId:Int){
        viewModelScope.launch {
            _movieDetail.value = Resource.Loading()
            try {
                val movie = movieRepository.getMovieDetails(movieId)
                _movieDetail.value = movie
            } catch (e: Exception) {
                _movieDetail.value = Resource.Failure(e)
            }
        }
    }
    fun serieDetail(serieId:Int){
        viewModelScope.launch {
            _serieDetail.value = Resource.Loading()
            try {
                val serie = movieRepository.getSeriesDetail(serieId)
                _serieDetail.value = serie
            } catch (e: Exception) {
                _serieDetail.value = Resource.Failure(e)
            }
        }
    }
}