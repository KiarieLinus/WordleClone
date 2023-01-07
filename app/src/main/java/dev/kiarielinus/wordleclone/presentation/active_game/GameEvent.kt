package dev.kiarielinus.wordleclone.presentation.active_game

sealed class GameEvent {
    data class OnInput(val input: Int) : GameEvent()
    data class OnTileFocused(val x: Int, val y: Int) : GameEvent()
    object OnNewGameClicked : GameEvent()
    object OnStart : GameEvent()
    object OnStop : GameEvent()
}