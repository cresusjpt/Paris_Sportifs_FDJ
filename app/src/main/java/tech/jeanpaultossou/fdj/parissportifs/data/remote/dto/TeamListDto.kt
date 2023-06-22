package tech.jeanpaultossou.fdj.parissportifs.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TeamListDto(

    @SerializedName("teams")
    val teamDto: List<TeamDto>
)