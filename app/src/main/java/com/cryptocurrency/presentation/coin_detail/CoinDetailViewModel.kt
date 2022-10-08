package com.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptocurrency.common.Constants
import com.cryptocurrency.common.Resource
import com.cryptocurrency.domain.usecases.getCoinDetail.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUsecase: GetCoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf<CoinDetailListState>(CoinDetailListState())
    val state : State<CoinDetailListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            coinId -> getCoinDetail(coinId)
        }
    }
    private fun getCoinDetail(coinId: String) {
        getCoinDetailUsecase(coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinDetailListState(coinDetail = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
