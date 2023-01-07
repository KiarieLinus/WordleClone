package dev.kiarielinus.wordleclone.data.remote.repository

import dev.kiarielinus.wordleclone.data.remote.response.api.DictionaryApi
import dev.kiarielinus.wordleclone.data.remote.response.api.RandomWordApi
import dev.kiarielinus.wordleclone.domain.repository.IWordleRepository
import dev.kiarielinus.wordleclone.util.Resource
import retrofit2.HttpException
import java.io.IOException

class WordleRepositoryImpl(
    private val randomWordApi: RandomWordApi,
    private val dictionaryApi: DictionaryApi
) : IWordleRepository {
    override suspend fun validateWord(word: String): Resource<Boolean> {
        return try {
            val response = dictionaryApi.validateWord(word)
            //if the response is empty or contains a String values then it's not valid
            if (response.isEmpty() || response.first()::class.simpleName == "String") {
                Resource.Success(data = false)
            } else {
                Resource.Success(data = true)
            }
        } catch (e: IOException) {
            Resource.Error(message = "Could not reach server. Try again later")
        } catch (e: HttpException) {
            Resource.Error(message = "Please check your internet connection and try again")
        }
    }

    override suspend fun getWord(length: Int): Resource<String> {
        return try {
            val response = randomWordApi.getWord(length).first()
            Resource.Success(data = response)
        } catch (e: IOException) {
            Resource.Error(message = "Could not reach server. Try again later")
        } catch (e: HttpException) {
            Resource.Error(message = "Please check your internet connection and try again")
        }
    }
}