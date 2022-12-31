package dev.kiarielinus.wordleclone.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kiarielinus.wordleclone.domain.repository.WordleRepository
import dev.kiarielinus.wordleclone.util.Resource
import kotlinx.coroutines.launch

class WordleViewModel(
    private val repository: WordleRepository
): ViewModel() {

    fun validateWord(word: String){
        viewModelScope.launch {
            when(repository.validateWord(word)){
                is Resource.Error -> TODO()
                is Resource.Success -> TODO()
            }
        }
    }
}