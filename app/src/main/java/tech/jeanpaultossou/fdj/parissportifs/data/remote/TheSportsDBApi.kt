package tech.jeanpaultossou.fdj.parissportifs.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.jeanpaultossou.fdj.parissportifs.core.Constants
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.LeagueDto
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.LeagueListDto
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.TeamDto
import tech.jeanpaultossou.fdj.parissportifs.data.remote.dto.TeamListDto

interface TheSportsDBApi {

    @GET("/api/v1/json/${Constants.API_KEY}/all_leagues.php")
    suspend fun getLeagues():LeagueListDto

    @GET("/api/v1/json/${Constants.API_KEY}/search_all_teams.php")
    suspend fun getTeams(@Query("l") leagueName:String): TeamListDto

    @GET("/api/v1/json/${Constants.API_KEY}/searchteams.php")
    suspend fun getTeam(@Query("t") teamName: String): TeamDto
}