package tech.jeanpaultossou.fdj.parissportifs.domain.use_case.get_team

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tech.jeanpaultossou.fdj.parissportifs.core.Resource
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.toTeam
import tech.jeanpaultossou.fdj.parissportifs.domain.model.Team
import tech.jeanpaultossou.fdj.parissportifs.domain.repository.TeamRepository
import java.io.IOException
import javax.inject.Inject

class GetTeamUC @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke(teamName: String): Flow<Resource<Team>> = flow{
        try {
            emit(Resource.Loading())
            val team = repository.getTeam(teamName).toTeam()
            emit(Resource.Success(team))
        }catch (httpE:HttpException){
            emit(Resource.Error(httpE.localizedMessage ?: "Erreur de chargment"))
        }catch (e: IOException){
            emit(Resource.Error("Vérifiez la connxion internet et réessayez svp."))
        }

    }
}