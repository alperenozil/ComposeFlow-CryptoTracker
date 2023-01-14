package alperen.ozil.cryptotracker.data.repository

import alperen.ozil.cryptotracker.data.remotedata.CoinApi
import alperen.ozil.cryptotracker.data.remotedata.dto.CoinDetailDto
import alperen.ozil.cryptotracker.data.remotedata.dto.CoinDto
import alperen.ozil.cryptotracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}