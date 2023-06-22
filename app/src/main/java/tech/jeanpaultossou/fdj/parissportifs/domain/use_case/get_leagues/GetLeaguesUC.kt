package tech.jeanpaultossou.fdj.parissportifs.domain.use_case.get_leagues

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tech.jeanpaultossou.fdj.parissportifs.core.Constants.TAG
import tech.jeanpaultossou.fdj.parissportifs.core.Resource
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.toLeague
import tech.jeanpaultossou.fdj.parissportifs.domain.model.League
import tech.jeanpaultossou.fdj.parissportifs.domain.repository.LeagueRepository
import java.io.IOException
import javax.inject.Inject
import kotlin.math.log

class GetLeaguesUC @Inject constructor(
    private val repository: LeagueRepository
) {
    //in replace of an execute method
    operator fun invoke():Flow<Resource<List<League>>> = flow {
        try {
            emit(Resource.Loading())
            val leagues = repository.getLeagues()
                .leagueDto.map {
                it.toLeague()
            }
            emit(Resource.Success(leagues))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Erreur de chargement."))
            Log.e(TAG, "invoke: getLeagueUC", e)
        }catch (e:IOException){
            emit(Resource.Error("Vérifiez votre connexion internet"))
        }
    }
}