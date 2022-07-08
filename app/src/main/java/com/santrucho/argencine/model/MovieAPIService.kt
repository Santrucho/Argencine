package com.santrucho.argencine.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieAPIService {

    /* companion object {
         private const val BASE_URL = "https://api.themoviedb.org"
         private var retrofit: Retrofit? = null

          fun getInstance(): Retrofit {
             if (retrofit == null) {
                 retrofit = Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build()
             }
             return retrofit!!
         } */

         val webservice: WebService by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(WebService::class.java)
        }
}