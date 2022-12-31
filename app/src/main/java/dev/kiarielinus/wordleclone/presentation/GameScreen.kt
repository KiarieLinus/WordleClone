package dev.kiarielinus.wordleclone.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun GameScreen() {
    Scaffold(
        topBar = { GameHeader() }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            //references
            val (game, keyboard) = createRefs()

            Keyboard(
                modifier = Modifier
                    .constrainAs(keyboard){
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = 8.dp)
            )

            GameDisplay(
                modifier = Modifier
                    .constrainAs(game){
                        top.linkTo(parent.top)
                        bottom.linkTo(keyboard.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}