package dev.kiarielinus.wordleclone.data.remote.response.api

import dev.kiarielinus.wordleclone.data.remote.response.dto.DictionaryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DictionaryApi {

    @GET("{word}")
    suspend fun validateWord(
        @Path("word") word: String,
        @Query("key") key: String = "b47c0a90-a49e-4b13-a888-970de548eacf"
    ): List<DictionaryDto>

    companion object {
        const val BASE_URL = "https://dictionaryapi.com/api/v3/references/collegiate/json/"
    }
}

interface RandomWordApi {

    @GET("word")
    suspend fun getWord(
        @Query("length") length: Int = 5
    ): List<String>

    companion object {
        const val BASE_URL = "https://random-word-api.herokuapp.com/"
    }
}