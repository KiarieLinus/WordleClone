package dev.kiarielinus.wordleclone.presentation.active_game

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kiarielinus.wordleclone.domain.model.WordleCell
import dev.kiarielinus.wordleclone.domain.repository.IWordleRepository
import dev.kiarielinus.wordleclone.util.Resource
import dev.kiarielinus.wordleclone.util.buttons
import kotlinx.coroutines.launch

class WordleViewModel(
    private val repository: IWordleRepository
) : ViewModel() {
    private val charGuesses = mutableListOf<String>()

    val subWordleCellStates = mutableListOf<MutableState<WordleCell>>()

    //Stores the word to be guessed
    private var wordToGuess = ""

    //Stores the word guessed on enter
    private lateinit var wordGuessed: String

    private var isWordCorrect: Boolean = false

    init {
        (0..29).map { index ->
            subWordleCellStates.add(
                mutableStateOf(
                    WordleCell(
                        isFirstInRow = if (index == 0) false else index.mod(5) == 0
                    )
                )
            )
        }

        getWord(5)
    }

    private val startRowStates = subWordleCellStates.filterIndexed { index, _ ->
        index.mod(5) == 0
    }
    private var currentRowIndex = 0

    private fun validateWord(
        word: String,
        onSuccess: (isWordValid: Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            when (val result = repository.validateWord(word)) {
                is Resource.Success -> {
                    Log.e("validateWord", result.data!!.toString())
                    onSuccess(result.data)
                }
                is Resource.Error -> {
                    Log.e("validateWord", result.message ?: "Unknown error")
                    onError(result.message ?: "Unknown error")
                }
            }
        }
    }

    private fun getWord(length: Int) {
        viewModelScope.launch {
            when (val result = repository.getWord(length)) {
                is Resource.Error -> Log.e("GetWord", result.message ?: "Unknown error")
                is Resource.Success -> {
                    Log.e("GetWord", result.data!!)
                    wordToGuess = result.data
                }
            }
        }
    }

    fun keyClicked(index: Int) {
        if (!isWordCorrect) {
            when (index) {
                19 -> {
                    enterPressed()
                }
                27 -> {
                    backspacePressed()
                }
                else -> {
                    if (charGuesses.lastIndex != 29 &&
                        !subWordleCellStates[charGuesses.lastIndex + 1].value.isFirstInRow
                    ) {
                        charGuesses.add(buttons[index])
                        subWordleCellStates[charGuesses.lastIndex].value =
                            subWordleCellStates[charGuesses.lastIndex].value
                                .copy(guess = charGuesses[charGuesses.lastIndex])
                    }
                }
            }
        }
    }

    private fun backspacePressed() {
        if (subWordleCellStates[charGuesses.lastIndex].value.isDeletable) {
            if (charGuesses.lastIndex != -1) {
                subWordleCellStates[charGuesses.lastIndex].value =
                    subWordleCellStates[charGuesses.lastIndex].value
                        .copy(guess = "")
                charGuesses.removeLastOrNull()
            }
        }
    }

    private fun enterPressed() {
        val activeRowFirstIndex = currentRowIndex * 5
        wordGuessed = charGuesses.subList(activeRowFirstIndex, activeRowFirstIndex + 5)
            .joinToString("").lowercase()
        validateWord(
            wordGuessed,
            onSuccess = { isWordValid ->
                if (startRowStates[currentRowIndex].value.guess.isNotEmpty() && isWordValid) {
                    if (charGuesses.size.mod(5) == 0 && startRowStates.lastIndex >= currentRowIndex) {
                        val firstRowIndex =
                            if (startRowStates.lastIndex == currentRowIndex) currentRowIndex
                            else (currentRowIndex + 1)
                        startRowStates[firstRowIndex].value =
                            startRowStates[firstRowIndex].value.copy(
                                isFirstInRow = false
                            )

                        subWordleCellStates[charGuesses.lastIndex].value =
                            subWordleCellStates[charGuesses.lastIndex].value.copy(
                                isDeletable = false
                            )

                        isWordCorrect = wordGuessed.isWordCorrect()

                        Log.e("StringGuess", "$wordGuessed , $currentRowIndex")

                        currentRowIndex++
                    }
                }
            },
            onError = {
                //Launch error message here
            })
    }

    private fun String.isWordCorrect(): Boolean {
        return this == wordToGuess
    }
}