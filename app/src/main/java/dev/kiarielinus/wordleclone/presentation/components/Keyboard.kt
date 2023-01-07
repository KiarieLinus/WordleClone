package dev.kiarielinus.wordleclone.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiarielinus.wordleclone.ui.theme.KeyGray
import dev.kiarielinus.wordleclone.util.buttons

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    keyClicked: (index: Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(start = 6.dp)
            .fillMaxWidth()
    ) {
        Row {
            for (key in 0..9) {
                KeyButton(modifier = Modifier.weight(1f), input = buttons[key], key) {
                    keyClicked(key)
                }
            }
        }
        Row {
            Spacer(modifier = Modifier.weight(0.5f))
            for (key in 10..18) {
                KeyButton(modifier = Modifier.weight(1f), input = buttons[key], key) {
                    keyClicked(key)
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }
        Row {
            for (key in 19..27) {
                KeyButton(
                    modifier = Modifier.weight(if (key == 19 || key == 27) 1.5f else 1f),
                    input = buttons[key],
                    key
                ) { keyClicked(key) }
            }
        }
    }
}

@Composable
private fun KeyButton(
    modifier: Modifier = Modifier,
    input: String,
    index: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(end = 6.dp)
            .clickable { onClick() }
            .height(60.dp)
            .background(KeyGray, RoundedCornerShape(4.dp))
    ) {
        if (index == 27) {
            Icon(
                imageVector = Icons.Outlined.Backspace,
                contentDescription = "Backspace",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        } else Text(
            text = input,
            color = Color.White,
            fontSize = if (index == 19) 12.sp else 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}