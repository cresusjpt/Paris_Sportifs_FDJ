package tech.jeanpaultossou.fdj.parissportifs.presentation.league_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.jeanpaultossou.fdj.parissportifs.core.Resource
import tech.jeanpaultossou.fdj.parissportifs.domain.use_case.get_leagues.GetLeaguesUC
import javax.inject.Inject

@HiltViewModel
class LeagueListViewModel @Inject constructor(
    private val getLeaguesUC: GetLeaguesUC
) : ViewModel() {

    private val _leagueState = mutableStateOf(LeagueListState())
    val leagueState: State<LeagueListState> = _leagueState

    init {
        getLeagues()
    }

    private fun getLeagues() {
        getLeaguesUC().onEach { res ->
            when (res) {
                is Resource.Loading -> {
                    _leagueState.value = LeagueListState(isLoading = true)
                }

                is Resource.Error -> {
                    _leagueState.value = LeagueListState(
                        error = res.message ?: "Une erreur est survenue lors du chargement"
                    )
                }

                is Resource.Success -> {
                    _leagueState.value = LeagueListState(leagues = res.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}