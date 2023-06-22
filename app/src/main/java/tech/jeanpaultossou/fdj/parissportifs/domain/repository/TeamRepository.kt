package tech.jeanpaultossou.fdj.parissportifs.domain.repository

import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.TeamDto
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.TeamListDto

interface TeamRepository {

    suspend fun getTeams(leagueName:String):TeamListDto

    suspend fun getTeam(teamName:String): TeamDto
}