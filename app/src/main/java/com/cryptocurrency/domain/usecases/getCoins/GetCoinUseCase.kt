package com.cryptocurrency.domain.usecases.getCoins

import com.cryptocurrency.common.Resource
import com.cryptocurrency.data.data_sources.dto.toCoin
import com.cryptocurrency.domain.model.Coin
import com.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator  fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        }catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Some error occured"))
        }catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet Connection"))
        }
    }
}