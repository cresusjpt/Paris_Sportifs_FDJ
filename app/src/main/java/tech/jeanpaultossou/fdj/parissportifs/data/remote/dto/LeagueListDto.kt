package tech.jeanpaultossou.fdj.parissportifs.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LeagueListDto(

    @SerializedName("leagues")
    val leagueDto: List<LeagueDto>
)
