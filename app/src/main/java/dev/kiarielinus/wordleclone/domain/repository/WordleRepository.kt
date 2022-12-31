package dev.kiarielinus.wordleclone.domain.repository

import dev.kiarielinus.wordleclone.util.Resource

interface WordleRepository {
    //Return true for valid word
    suspend fun validateWord(word: String): Resource<Boolean>

    suspend fun getWord(length: Int): Resource<String>
}