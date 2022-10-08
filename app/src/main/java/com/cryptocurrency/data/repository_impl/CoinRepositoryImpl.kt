package com.cryptocurrency.data.repository_impl

import com.cryptocurrency.data.data_sources.CoinPaprikaRemoteDataSource
import com.cryptocurrency.data.data_sources.dto.CoinDetailDto
import com.cryptocurrency.data.data_sources.dto.CoinsDto
import com.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaRemoteDataSource
) : CoinRepository {

    override suspend fun getCoins(): List<CoinsDto> {
        return api.getCoins();
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}