package com.santrucho.argencine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santrucho.argencine.model.Repo
import com.santrucho.argencine.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo) : ViewModel() {

    val fetchMoviesList = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getMovieList(""))
        }
        catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}