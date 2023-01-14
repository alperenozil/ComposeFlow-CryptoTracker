package alperen.ozil.cryptotracker.presentation.coinlist

import alperen.ozil.cryptotracker.common.Resource
import alperen.ozil.cryptotracker.domain.usecase.getcoins.GetCoinsUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _coinListState = mutableStateOf(CoinListState())
    // The UI collects from this State to get its state updates
    val coinListState: State<CoinListState> = _coinListState

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinListState.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _coinListState.value = CoinListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _coinListState.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}