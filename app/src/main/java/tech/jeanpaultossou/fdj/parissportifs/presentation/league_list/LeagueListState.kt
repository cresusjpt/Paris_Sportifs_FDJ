package tech.jeanpaultossou.fdj.parissportifs.presentation.league_list

import tech.jeanpaultossou.fdj.parissportifs.domain.model.League

data class LeagueListState(
    val isLoading: Boolean = false,
    val leagues: List<League> = emptyList(),
    val error:String = ""
)
