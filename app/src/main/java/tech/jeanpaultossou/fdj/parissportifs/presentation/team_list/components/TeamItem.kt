package tech.jeanpaultossou.fdj.parissportifs.presentation.team_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import tech.jeanpaultossou.fdj.parissportifs.domain.model.Team
import tech.jeanpaultossou.fdj.parissportifs.presentation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamItem(
    navController: NavController,
    team: Team,
) {
    Card(
        modifier = Modifier.padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = {
            //navController.navigate(Screen.TeamScreen.route)
        },
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(team.teamImage),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .alpha(1f)
                    .aspectRatio(1f)
                    //.rotate(listficheTSO[index].rotate)
                    .alpha(.9f)
                    .padding(
                        start = 5.dp,
                        end = 5.dp
                    ),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = team.strTeam,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}