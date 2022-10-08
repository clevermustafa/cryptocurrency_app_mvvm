package com.cryptocurrency.domain.repository

import com.cryptocurrency.data.data_sources.dto.CoinDetailDto
import com.cryptocurrency.data.data_sources.dto.CoinsDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinsDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto

}