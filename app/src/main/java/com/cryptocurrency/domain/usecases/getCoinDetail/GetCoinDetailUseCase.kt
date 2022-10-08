package com.cryptocurrency.domain.usecases.getCoinDetail

import com.cryptocurrency.common.Resource
import com.cryptocurrency.data.data_sources.dto.toCoinDetail
import com.cryptocurrency.domain.model.CoinDetail
import com.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator  fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "Some error occured"))
        }catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet Connection"))
        }
    }
}