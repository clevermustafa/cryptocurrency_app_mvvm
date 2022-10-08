package com.cryptocurrency.presentation.coin_list

import com.cryptocurrency.domain.model.Coin


data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
