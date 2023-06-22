package tech.jeanpaultossou.fdj.parissportifs.presentation.team_list

import tech.jeanpaultossou.fdj.parissportifs.domain.model.Team

data class TeamListState(
    val isLoading: Boolean = false,
    val teams: List<Team> = emptyList(),
    val error:String = ""
)
