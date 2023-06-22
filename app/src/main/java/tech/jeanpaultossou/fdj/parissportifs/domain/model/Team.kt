package tech.jeanpaultossou.fdj.parissportifs.domain.model

import coil.request.ImageRequest

data class Team (
    val idLeague: String,
    val idLeague2: String?,
    val idLeague3: String?,
    val idLeague4: String?,
    val idLeague5: Any?,
    val idLeague6: Any?,
    val idLeague7: Any?,
    val idTeam: String?,
    val strFacebook: String?,
    val strGender: String?,
    val strInstagram: String?,
    val strKeywords: String?,
    val strKitColour1: String?,
    val strKitColour2: String?,
    val strKitColour3: String?,
    val strLeague: String,
    val strLeague2: String?,
    val strLeague3: String?,
    val strLeague4: String?,
    val strLeague5: String?,
    val strLeague6: String?,
    val strLeague7: String?,
    val strSport: String?,
    val strStadium: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val strStadiumThumb: String?,
    val strTeam: String,
    val strTeamBadge: String?,
    val strTeamBanner: String?,
    val strTeamLogo: String?,
    val strTeamShort: String?,
    val strTwitter: String?,
    val strWebsite: String?,
    val strYoutube: String?,
    var teamImage:ImageRequest?

):Comparable<Team>{
    override fun compareTo(other: Team): Int {
        return this.strTeam compareTo other.strTeam
    }
}