package dev.kiarielinus.wordleclone.presentation

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
    private val _charGuesses = mutableListOf<String>()

    val guesses = mutableListOf<MutableState<GameDisplayState>>()

    init {
        (0..29).map { index ->
            guesses.add(
                mutableStateOf(
                    GameDisplayState(
                        isFirstInRow = if (index == 0) false else index.mod(5) == 0
                    )
                )
            )
        }
    }

    private val startRowStates = guesses.filterIndexed { index, _ ->
        index.mod(5) == 0 && index != 0
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
            when (repository.getWord(length)) {
                is Resource.Error -> TODO()
                is Resource.Success -> TODO()
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
                if (_charGuesses.lastIndex != 29 && !guesses[_charGuesses.lastIndex + 1].value.isFirstInRow) {
                    _charGuesses.add(buttons[index])
                    guesses[_charGuesses.lastIndex].value = guesses[_charGuesses.lastIndex].value
                        .copy(guess = _charGuesses[_charGuesses.lastIndex])
                }
            }
        }
    }

    private fun backspacePressed() {
//        Log.e("CurrentRowLC", "current row: ${currentRowIndex*5+5}, lastInd: ${_charGuesses.lastIndex}")
        if (guesses[_charGuesses.lastIndex].value.isDeletable) {
            if (_charGuesses.lastIndex != -1) {
                guesses[_charGuesses.lastIndex].value = guesses[_charGuesses.lastIndex].value
                    .copy(guess = "")
                _charGuesses.removeLastOrNull()
            }
        }
    }

    private fun enterPressed() {
        if (_charGuesses.size != 0 && _charGuesses.size.mod(5) == 0) {
            startRowStates[currentRowIndex].value = startRowStates[currentRowIndex].value.copy(
                isFirstInRow = false
            )

            guesses[_charGuesses.lastIndex].value = guesses[_charGuesses.lastIndex].value.copy(
                isDeletable = false
            )

            if (startRowStates.lastIndex > currentRowIndex) {
                currentRowIndex++
            }
        }
    }
}