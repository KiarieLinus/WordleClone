package dev.kiarielinus.wordleclone.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiarielinus.wordleclone.ui.theme.KeyGray
import dev.kiarielinus.wordleclone.util.buttons

@Preview
@Composable
fun Keyboard(
    modifier: Modifier = Modifier
) {
    var size by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
            .onGloballyPositioned {
                size = it.size.width
                Log.e("ColumnWidth", size.toString())
            }
            .fillMaxWidth()
    ) {
        val localDensity = LocalDensity.current.density
        val width = (size/localDensity - 70).dp
        Log.e("ColumnWidth", size.toString())
        val keyWidth = width/10

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            for (i in 0..9) {
                KeyButton(input = buttons[i], i,keyWidth ) {}
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            for (i in 10..18) {
                KeyButton(input = buttons[i], i,keyWidth) {}
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            for (i in 19..27) {
                KeyButton(input = buttons[i], i,keyWidth) {}
            }
        }
    }
}

@Composable
private fun KeyButton(
    input: String,
    index: Int,
    width: Dp,
    onClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .clickable { onClick() }
            .width(if (index == 19 || index == 27) ((width * 1.5f)+ 3.dp) else width)
            .height(width*2)
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
            fontSize = if(index == 19) 12.sp else 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}