package alperen.ozil.cryptotracker.data.remotedata.dto

import alperen.ozil.cryptotracker.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toModel() : Coin {
    return Coin(id = id, isActive = isActive, name = name, rank = rank, symbol = symbol)
}
