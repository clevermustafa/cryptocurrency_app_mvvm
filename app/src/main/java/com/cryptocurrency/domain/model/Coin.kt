package com.cryptocurrency.domain.model

data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val type: String,
    val symbol: String
)
