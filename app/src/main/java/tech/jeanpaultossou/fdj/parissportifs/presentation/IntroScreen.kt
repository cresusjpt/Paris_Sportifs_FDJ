package tech.jeanpaultossou.fdj.parissportifs.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import tech.jeanpaultossou.fdj.parissportifs.presentation.league_list.LeagueListScreen
import tech.jeanpaultossou.fdj.parissportifs.presentation.team_list.TeamListScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IntroScreen(
    navController: NavController
) {
    LeagueListScreen(navController = navController)
    TeamListScreen(navController = navController)
}