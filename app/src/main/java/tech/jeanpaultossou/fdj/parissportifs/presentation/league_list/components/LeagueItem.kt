package tech.jeanpaultossou.fdj.parissportifs.presentation.league_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import tech.jeanpaultossou.fdj.parissportifs.domain.model.League

@Composable
fun LeagueItem(
    league: League,
    onClickItem: (League)->Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickItem(league) }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = league.strLeague,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = league.strSport,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = Italic,
            textAlign = TextAlign.End,
            modifier = Modifier.align(CenterVertically)
        )
    }
}