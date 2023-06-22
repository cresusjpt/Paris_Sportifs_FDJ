package tech.jeanpaultossou.fdj.parissportifs.data.repository

import tech.jeanpaultossou.fdj.parissportifs.data.remote.TheSportsDBApi
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.TeamDto
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.TeamListDto
import tech.jeanpaultossou.fdj.parissportifs.domain.repository.TeamRepository
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val api: TheSportsDBApi
): TeamRepository {
    override suspend fun getTeams(leagueName: String): TeamListDto{
        return api.getTeams(leagueName)
    }

    override suspend fun getTeam(teamName: String): TeamDto {
        return api.getTeam(teamName)
    }
}