package tech.jeanpaultossou.fdj.parissportifs.data.remote.dto

import tech.jeanpaultossou.fdj.parissportifs.domain.model.League

data class LeagueDto(
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String?,
    val strSport: String
)

fun LeagueDto.toLeague():League{
    return League(
        idLeague = idLeague,
        strLeague = strLeague,
        strLeagueAlternate = strLeagueAlternate,
        strSport = strSport
    )
}