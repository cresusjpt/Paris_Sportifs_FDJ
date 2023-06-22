package tech.jeanpaultossou.fdj.parissportifs.presentation.team_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.jeanpaultossou.fdj.parissportifs.presentation.league_list.LeagueListScreen
import tech.jeanpaultossou.fdj.parissportifs.presentation.team_list.components.TeamItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TeamListScreen(
    navController: NavController,
    viewModel: TeamListViewModel = hiltViewModel(),
) {
    val state:TeamListState = viewModel.teamListState.value
    val teams = state.teams
    Column {
        LeagueListScreen(navController = navController)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            //Modifier.background(colorResource(id = R.color.white)),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                bottom = 16.dp,
                end = 12.dp
            ),

            content = {
                items(teams.size) { index ->
                    TeamItem(navController = navController, team = teams[index])
                }
            }
        )
    }
}