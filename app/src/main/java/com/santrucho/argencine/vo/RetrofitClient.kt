package com.santrucho.argencine.vo

import com.google.gson.GsonBuilder
import com.santrucho.argencine.data.network.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

         val webservice: WebService by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(WebService::class.java)
        }
}