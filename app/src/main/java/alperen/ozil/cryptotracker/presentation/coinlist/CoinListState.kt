package alperen.ozil.cryptotracker.presentation.coinlist

import alperen.ozil.cryptotracker.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)