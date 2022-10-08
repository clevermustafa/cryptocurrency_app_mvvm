package com.cryptocurrency.presentation.coin_detail

import com.cryptocurrency.domain.model.Coin
import com.cryptocurrency.domain.model.CoinDetail


data class CoinDetailListState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)
