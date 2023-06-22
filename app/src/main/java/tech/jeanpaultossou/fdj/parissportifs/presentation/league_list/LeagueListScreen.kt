package tech.jeanpaultossou.fdj.parissportifs.presentation.league_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.jeanpaultossou.fdj.parissportifs.presentation.Screen
import tech.jeanpaultossou.fdj.parissportifs.presentation.league_list.components.SearchableAutoComplete

@ExperimentalAnimationApi
@Composable
fun LeagueListScreen(
    navController: NavController,
    viewModel: LeagueListViewModel = hiltViewModel(),
) {
    val state = viewModel.leagueState.value
    val leagues = state.leagues

    Column {
        SearchableAutoComplete(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            items = leagues,
            onItemSelected = {},
            isLoading = state.isLoading,
            isError = state.error.isBlank(),
            error = state.error,
            onItemClicked = {
                navController.navigate(Screen.TeamListScreen.route + "/${it.strLeague}")
            }
        )


        if (state.error.isNotBlank())
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
            )
    }

}