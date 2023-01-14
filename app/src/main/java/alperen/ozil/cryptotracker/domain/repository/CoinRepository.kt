package alperen.ozil.cryptotracker.domain.repository

import alperen.ozil.cryptotracker.data.remotedata.dto.CoinDetailDto
import alperen.ozil.cryptotracker.data.remotedata.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}