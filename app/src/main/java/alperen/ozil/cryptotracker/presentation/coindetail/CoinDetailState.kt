package alperen.ozil.cryptotracker.presentation.coindetail

import alperen.ozil.cryptotracker.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)