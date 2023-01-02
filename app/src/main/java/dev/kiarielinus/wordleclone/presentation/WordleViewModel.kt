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
    //Get the list of index and letter only pair buttons
    private val letters = buttons.mapIndexed { index, value ->
        index to value
    }.filterNot {
        it.first == 19 || it.first == 27
    }

    private val _charGuesses = mutableListOf<String>()

    val guesses = mutableListOf<MutableState<GameDisplayState>>()

    init {
        (0..29).map {
            guesses.add(mutableStateOf(GameDisplayState()))
        }
    }

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
                if ((_charGuesses.lastIndex != 29)) {
                    _charGuesses.add(buttons[index])
                    guesses[_charGuesses.lastIndex].value = guesses[_charGuesses.lastIndex].value
                        .copy(guess = _charGuesses[_charGuesses.lastIndex])
                }
            }
        }
    }

    private fun backspacePressed() {
        if (_charGuesses.lastIndex != -1) {
            guesses[_charGuesses.lastIndex].value = guesses[_charGuesses.lastIndex].value
                .copy(guess = "")
            _charGuesses.removeLastOrNull()
        }
    }

    private fun enterPressed() {
        TODO()
    }

}