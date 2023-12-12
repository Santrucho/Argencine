package com.santrucho.argencine.vo

import com.santrucho.argencine.data.network.WebService
import com.santrucho.argencine.domain.repo.MovieRepository
import com.santrucho.argencine.domain.repo.MovieRepositoryImpl
import com.santrucho.argencine.vo.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //TODO Inyect repositorys and retrofit with Dagger Hilt in whole app

    @Provides
    fun provideRepository(implementation : MovieRepositoryImpl) : MovieRepository = implementation

    @Provides
    fun provideApiService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}