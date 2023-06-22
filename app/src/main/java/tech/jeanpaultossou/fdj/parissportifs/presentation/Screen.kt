package tech.jeanpaultossou.fdj.parissportifs.presentation

sealed class Screen(val route:String){
    object LeagueListScreen : Screen("league_list_screen")
    object TeamListScreen : Screen("team_list_screen")
    object TeamScreen : Screen("team_screen")
}
