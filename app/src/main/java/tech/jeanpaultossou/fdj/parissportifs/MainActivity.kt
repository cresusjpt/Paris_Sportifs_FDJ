package tech.jeanpaultossou.fdj.parissportifs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import tech.jeanpaultossou.fdj.parissportifs.core.Constants
import tech.jeanpaultossou.fdj.parissportifs.presentation.IntroScreen
import tech.jeanpaultossou.fdj.parissportifs.presentation.Screen
import tech.jeanpaultossou.fdj.parissportifs.presentation.league_list.LeagueListScreen
import tech.jeanpaultossou.fdj.parissportifs.presentation.team_list.TeamListScreen
import tech.jeanpaultossou.fdj.parissportifs.ui.theme.ParisSportifsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParisSportifsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        //startDestination = Screen.TeamListScreen.route,
                        startDestination = Screen.LeagueListScreen.route,
                    ) {
                        composable(
                            route = Screen.LeagueListScreen.route //+ "/{${Constants.LEAGUE_PARAM}}"
                        ) {
                            LeagueListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.TeamListScreen.route + "/{${Constants.LEAGUE_PARAM}}"
                        ) {
                            TeamListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.TeamScreen.route
                        ) {

                        }
                    }
                }
            }
        }
    }
}