package com.cryptocurrency.di

import com.cryptocurrency.common.Constants
import com.cryptocurrency.data.data_sources.CoinPaprikaRemoteDataSource
import com.cryptocurrency.data.repository_impl.CoinRepositoryImpl
import com.cryptocurrency.domain.repository.CoinRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPaprikaApi(): CoinPaprikaRemoteDataSource {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaRemoteDataSource): CoinRepository {
        return  CoinRepositoryImpl(api);
    }
}