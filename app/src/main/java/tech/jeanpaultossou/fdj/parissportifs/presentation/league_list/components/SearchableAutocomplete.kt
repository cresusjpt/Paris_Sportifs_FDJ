package tech.jeanpaultossou.fdj.parissportifs.presentation.league_list.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.filter
import tech.jeanpaultossou.fdj.parissportifs.core.Constants
import tech.jeanpaultossou.fdj.parissportifs.domain.model.League
import tech.jeanpaultossou.fdj.parissportifs.domain.model.Team
import tech.jeanpaultossou.fdj.parissportifs.presentation.Screen

private val DropdownMenuVerticalPadding = 8.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableAutoComplete(
    modifier: Modifier = Modifier,
    items: List<League>,
    onItemSelected: (String) -> Unit,
    onItemClicked: (League) -> Unit,
    placeholder: String = "",
    isLoading: Boolean,
    isError: Boolean,
    error: String = ""
) {
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var selectedItem by remember { mutableStateOf(TextFieldValue("")) }
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions
            .filter { it is PressInteraction.Press }
            .collect {
                expanded = !expanded
            }
    }

    val itemHeights = remember { mutableStateMapOf<Int, Int>() }
    val baseHeight = 530.dp
    val density = LocalDensity.current

    val maxHeight = remember(itemHeights.toMap()) {
        if (itemHeights.keys.toSet() != items.indices.toSet()) {
            // if we don't have all heights calculated yet, return default value
            return@remember baseHeight
        }
        val baseHeightInt = with(density) { baseHeight.toPx().toInt() }

        // top+bottom system padding
        var sum = with(density) { DropdownMenuVerticalPadding.toPx().toInt() } * 2
        for ((_, itemSize) in itemHeights.toSortedMap()) {
            sum += itemSize
            if (sum >= baseHeightInt) {
                return@remember with(density) { (sum - itemSize / 2).toDp() }
            }
        }
        // all items fit into base height
        baseHeight
    }

    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        expanded = it.hasFocus
                    },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                value = selectedItem,
                onValueChange = {
                    selectedItem = it
                    onItemSelected(it.text)
                },
                trailingIcon = {
                    val rotation by animateFloatAsState(if (expanded) 180F else 0F)
                    IconButton(
                        onClick = { selectedItem = TextFieldValue("") }
                    ) {
                        Icon(
                            rememberVectorPainter(Icons.Default.Close),
                            contentDescription = "Dropdown",
                            Modifier.rotate(rotation),
                        )
                    }
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                },
                /*
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.displaySmall
                    )
                },
                 */
                supportingText = {
                    if (isError && (error.isNotEmpty() || error.isNotBlank()))
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.labelSmall,
                        )
                }
            )
            if (isLoading)
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )

            val filteredOptions =
                items.filter {
                    it.strLeague.contains(selectedItem.text, ignoreCase = true)
                }
            if (filteredOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth(.99f)
                        .requiredSizeIn(maxHeight = maxHeight / 2)
                        .background(Color.White),
                    expanded = expanded,
                    onDismissRequest = {
                        // We shouldn't hide the menu when the user enters/removes any character
                    }
                ) {
                    filteredOptions.forEach { item ->
                        DropdownMenuItem(
                            modifier = Modifier.background(Color.White),
                            text = {
                                Column(
                                    modifier = Modifier.wrapContentSize()
                                ) {
                                    Text(
                                        text = item.strLeague,
                                        modifier = Modifier.padding(bottom = 16.dp)
                                    )
                                }
                            },
                            onClick = {
                                selectedItem =
                                    TextFieldValue(
                                        text = item.strLeague,
                                        selection = TextRange(item.strLeague.length)
                                    )
                                expanded = false
                                onItemClicked(item)
                            }
                        )
                    }
                }
            }
        }
    }
}