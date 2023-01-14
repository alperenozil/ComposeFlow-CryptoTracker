package alperen.ozil.cryptotracker.domain.usecase.getsinglecoin

import alperen.ozil.cryptotracker.common.Resource
import alperen.ozil.cryptotracker.data.remotedata.dto.toCoinDetail
import alperen.ozil.cryptotracker.domain.model.CoinDetail
import alperen.ozil.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSingleCoinUseCase @Inject constructor(
    private val repo: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repo.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch(e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}