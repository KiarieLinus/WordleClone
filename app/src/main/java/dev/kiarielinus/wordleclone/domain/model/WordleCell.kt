package dev.kiarielinus.wordleclone.domain.model

data class WordleCell(
    val guess: String = "",
    val isFirstInRow: Boolean = false,
    val isDeletable: Boolean = true,
    val isInWord: Boolean = false,
    val isCorrectlyPlaced: Boolean = false
)