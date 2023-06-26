package tech.jeanpaultossou.fdj.parissportifs.data.repository

import tech.jeanpaultossou.fdj.parissportifs.data.remote.TheSportsDBApi
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.LeagueDto
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.LeagueListDto
import tech.jeanpaultossou.fdj.parissportifs.domain.model.League
import tech.jeanpaultossou.fdj.parissportifs.domain.repository.LeagueRepository
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    private val api: TheSportsDBApi
):LeagueRepository {


    override suspend fun getLeagues(): LeagueListDto {
        return api.getLeagues()
    }
}