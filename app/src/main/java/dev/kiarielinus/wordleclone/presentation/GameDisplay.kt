package dev.kiarielinus.wordleclone.presentation

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiarielinus.wordleclone.ui.theme.BorderGray
import dev.kiarielinus.wordleclone.ui.theme.KarnakCondensed

@Preview
@Composable
fun GameDisplay(
    modifier: Modifier = Modifier,
    difficulty: Int = 4
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
                    val cellPosition by remember { mutableStateOf(arrayOf(row,column)) }
                    Log.e("CellPosition", cellPosition.contentToString())
                    GuessCell(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun GuessCell(
    modifier: Modifier = Modifier,
    text: String = "B"
) {
    Box(
        modifier = modifier
            .border(2.dp, color = BorderGray)
            .aspectRatio(1f)
    ) {
        Text(
            text = text,
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
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
                Text(
                    text = "Wordle",
                    fontFamily = KarnakCondensed,
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.W700
                )
            }
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