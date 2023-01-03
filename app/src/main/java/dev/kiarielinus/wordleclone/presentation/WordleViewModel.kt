package dev.kiarielinus.wordleclone.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kiarielinus.wordleclone.domain.repository.WordleRepository
import dev.kiarielinus.wordleclone.util.Resource
import dev.kiarielinus.wordleclone.util.buttons
import kotlinx.coroutines.launch

class WordleViewModel(
    private val repository: WordleRepository
) : ViewModel() {
    private val charGuesses = mutableListOf<String>()

    val gameDisplayStates = mutableListOf<MutableState<GameDisplayState>>()

    init {
        (0..29).map { index ->
            gameDisplayStates.add(
                mutableStateOf(
                    GameDisplayState(
                        isFirstInRow = if (index == 0) false else index.mod(5) == 0
                    )
                )
            )
        }

        getWord(5)
    }

    private val startRowStates = gameDisplayStates.filterIndexed { index, _ ->
        index.mod(5) == 0
    }
    private var currentRowIndex = 0

    fun validateWord(word: String) {
        viewModelScope.launch {
            when (repository.validateWord(word)) {
                is Resource.Error -> TODO()
                is Resource.Success -> TODO()
            }
        }
    }

    fun getWord(length: Int) {
        viewModelScope.launch {
            when (val result = repository.getWord(length)) {
                is Resource.Error -> Log.e("GetWord", result.message ?: "Unknown error")
                is Resource.Success -> Log.e("GetWord", result.data ?: "Unknown success")
            }
        }
    }

    fun keyClicked(index: Int) {
        when (index) {
            19 -> {
                enterPressed()
            }
            27 -> {
                backspacePressed()
            }
            else -> {
                if (charGuesses.lastIndex != 29 &&
                    !gameDisplayStates[charGuesses.lastIndex + 1].value.isFirstInRow
                ) {
                    charGuesses.add(buttons[index])
                    gameDisplayStates[charGuesses.lastIndex].value =
                        gameDisplayStates[charGuesses.lastIndex].value
                            .copy(guess = charGuesses[charGuesses.lastIndex])
                }
            }
        }
    }

    private fun backspacePressed() {
        if (gameDisplayStates[charGuesses.lastIndex].value.isDeletable) {
            if (charGuesses.lastIndex != -1) {
                gameDisplayStates[charGuesses.lastIndex].value =
                    gameDisplayStates[charGuesses.lastIndex].value
                        .copy(guess = "")
                charGuesses.removeLastOrNull()
            }
        }
    }

    private fun enterPressed() {
        if (startRowStates[currentRowIndex].value.guess.isNotEmpty()) {
            if (charGuesses.size.mod(5) == 0 && startRowStates.lastIndex >= currentRowIndex) {
                val firstRowIndex =
                    if (startRowStates.lastIndex == currentRowIndex) currentRowIndex
                    else (currentRowIndex + 1)
                startRowStates[firstRowIndex].value =
                    startRowStates[firstRowIndex].value.copy(
                        isFirstInRow = false
                    )

                gameDisplayStates[charGuesses.lastIndex].value =
                    gameDisplayStates[charGuesses.lastIndex].value.copy(
                        isDeletable = false
                    )

                val wordGuessed = charGuesses.subList(currentRowIndex * 5, currentRowIndex * 5 + 5)
                    .joinToString("")

                Log.e("StringGuess", "$wordGuessed , $currentRowIndex")

                currentRowIndex++
            }
        }
    }
}