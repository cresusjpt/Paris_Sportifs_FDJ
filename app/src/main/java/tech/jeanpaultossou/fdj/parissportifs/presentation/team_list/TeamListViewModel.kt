package tech.jeanpaultossou.fdj.parissportifs.presentation.team_list

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tech.jeanpaultossou.fdj.parissportifs.core.Constants
import tech.jeanpaultossou.fdj.parissportifs.core.Resource
import tech.jeanpaultossou.fdj.parissportifs.domain.use_case.get_teams.GetTeamsUC
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(
    private val getTeamsUC: GetTeamsUC,
    @ApplicationContext context: Context,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _teamListState = mutableStateOf(TeamListState())
    val teamListState: State<TeamListState> = _teamListState

    init {
        //getTeams("Scottish Championship", context)
        savedStateHandle.get<String>(Constants.LEAGUE_PARAM)?.let {
            getTeams(it, context)
        }
    }

    private fun getTeams(leagueName: String, context: Context) {
        getTeamsUC(leagueName).onEach { res ->
            when (res) {
                is Resource.Loading -> {
                    _teamListState.value = TeamListState(isLoading = true)
                }

                is Resource.Error -> {
                    _teamListState.value = TeamListState(
                        error = res.message ?: "Une erreur est survenue lors du chargement"
                    )
                }

                is Resource.Success -> {
                    val teams = res.data ?: emptyList()
                    teams.map {

                        val request = ImageRequest.Builder(context)
                            .data(it.strTeamBadge)
                            .allowHardware(false) // Disable hardware bitmaps.
                            .build()
                        it.teamImage = request
                    }

                    //val loader = ImageLoader(context)
                    //val result = (loader.execute(request) as SuccessResult).drawable
                    //val bitmap = (result as BitmapDrawable).bitmap
                    _teamListState.value = TeamListState(teams = teams)
                }
            }
        }.launchIn(viewModelScope)
    }
}