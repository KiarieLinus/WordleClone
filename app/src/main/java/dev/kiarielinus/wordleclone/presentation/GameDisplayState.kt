package dev.kiarielinus.wordleclone.presentation

data class GameDisplayState(
    val guess: String = "",
    val isLastInRow: Boolean = false
)