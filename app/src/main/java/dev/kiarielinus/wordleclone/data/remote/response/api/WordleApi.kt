package dev.kiarielinus.wordleclone.data.remote.response.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DictionaryApi {

    //validateWord returns 3 types of lists depending on the searched word
    //1. An empty list when the word does not exist and they're no suggestions
    //2. A list of strings when the word does exist and they're suggestions
    //3. A list of the api parameters containing various definitions of the word
    //Using Any in this case covers the three types and serves the app
    //since all we need is to validate the third case and not necessarily have a definition
    @GET("{word}")
    suspend fun validateWord(
        @Path("word") word: String,
        @Query("key") key: String = "b47c0a90-a49e-4b13-a888-970de548eacf"
    ): List<Any>

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