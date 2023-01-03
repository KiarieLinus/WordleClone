package dev.kiarielinus.wordleclone.presentation

data class GameDisplayState(
    val guess: String = "",
    val isFirstInRow: Boolean = false,
    val isDeletable: Boolean = true
)