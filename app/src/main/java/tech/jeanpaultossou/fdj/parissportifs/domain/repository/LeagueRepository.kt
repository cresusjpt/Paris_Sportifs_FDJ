package tech.jeanpaultossou.fdj.parissportifs.domain.repository

import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.LeagueDto
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.LeagueListDto

interface LeagueRepository {
    suspend fun getLeagues():LeagueListDto
}