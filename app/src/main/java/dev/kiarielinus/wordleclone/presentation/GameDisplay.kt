package dev.kiarielinus.wordleclone.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiarielinus.wordleclone.ui.theme.BorderGray
import dev.kiarielinus.wordleclone.ui.theme.KarnakCondensed


@Composable
fun GameDisplay(
    modifier: Modifier = Modifier,
    difficulty: Int,
    state: MutableList<MutableState<GameDisplayState>>,
) {

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        for (row in 0..difficulty + 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                for (column in 0..difficulty) {
                    val index = row * (difficulty + 1) + column
                    GuessCell(modifier = Modifier.weight(1f), value = state[index].value.guess)
                }
            }
        }
    }
}

@Composable
private fun GuessCell(
    modifier: Modifier = Modifier,
    value: String,
) {
    Box(
        modifier = modifier
            .border(2.dp, color = BorderGray)
            .aspectRatio(1f)
    ) {
        Text(
            text = value,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}

@Preview
@Composable
fun GameHeader() {
    TopAppBar(
        navigationIcon = {
            Box(Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        title = {
            Text(
                text = "Wordle",
                letterSpacing = 1.sp,
                fontFamily = KarnakCondensed,
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.W700
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Outlined.Help,
                contentDescription = "help",
                modifier = Modifier.padding(horizontal = 4.dp),
                tint = Color.White
            )
            Icon(
                imageVector = Icons.Outlined.Leaderboard,
                contentDescription = "stats",
                modifier = Modifier.padding(horizontal = 4.dp),
                tint = Color.White
            )
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "settings",
                modifier = Modifier.padding(horizontal = 4.dp),
                tint = Color.White
            )
        }
    )
}