package com.cryptocurrency.data.data_sources

import com.cryptocurrency.data.data_sources.dto.CoinDetailDto
import com.cryptocurrency.data.data_sources.dto.CoinsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaRemoteDataSource {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinsDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto;
}