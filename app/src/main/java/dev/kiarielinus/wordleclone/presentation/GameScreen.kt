package dev.kiarielinus.wordleclone.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen() {
    Scaffold(
        topBar = { GameHeader() }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            GameDisplay(modifier = Modifier.align(Alignment.TopCenter))
            Keyboard(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp))
        }
    }
}