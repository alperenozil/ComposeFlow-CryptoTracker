package alperen.ozil.cryptotracker.domain.usecase.getcoins

import alperen.ozil.cryptotracker.common.Resource
import alperen.ozil.cryptotracker.data.remotedata.dto.toModel
import alperen.ozil.cryptotracker.domain.model.Coin
import alperen.ozil.cryptotracker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repo: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return flow {
            try {
                emit(Resource.Loading<List<Coin>>())
                val coins = repo.getCoins().map { it.toModel() }
                emit(Resource.Success<List<Coin>>(coins))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<Coin>>(
                        e.localizedMessage ?: "An unexpected error occured"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
            }
        }
    }
}