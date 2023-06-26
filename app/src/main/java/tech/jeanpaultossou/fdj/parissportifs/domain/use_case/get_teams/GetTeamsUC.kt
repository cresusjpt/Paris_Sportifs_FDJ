package tech.jeanpaultossou.fdj.parissportifs.domain.use_case.get_teams

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tech.jeanpaultossou.fdj.parissportifs.core.Resource
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.toTeam
import tech.jeanpaultossou.fdj.parissportifs.domain.model.Team
import tech.jeanpaultossou.fdj.parissportifs.domain.repository.TeamRepository
import java.io.IOException
import javax.inject.Inject

class GetTeamsUC @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke(leagueName: String): Flow<Resource<List<Team>>> = flow {
        try {
            emit(Resource.Loading())
            //order descending and get 1/2 of teams in the league
            val teams = repository.getTeams(leagueName).teamDto.map { it.toTeam() }
                .filterIndexed { index, _ ->
                    index % 2 == 0
                }
            emit(Resource.Success(teams.sortedDescending()))
        } catch (httpE: HttpException) {
            emit(Resource.Error(httpE.localizedMessage ?: "Erreur de chargment"))
        } catch (e: IOException) {
            emit(Resource.Error("Vérifiez la connxion internet et réessayez svp."))
        }

    }
}