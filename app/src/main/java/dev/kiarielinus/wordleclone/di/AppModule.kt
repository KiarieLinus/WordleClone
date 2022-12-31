package dev.kiarielinus.wordleclone.di

import dev.kiarielinus.wordleclone.data.remote.response.api.DictionaryApi
import dev.kiarielinus.wordleclone.data.remote.response.api.RandomWordApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    //Provides DictionaryApi singleton
    single {
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
    //Provides RandomWordApi singleton
    single {
        Retrofit.Builder()
            .baseUrl(RandomWordApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomWordApi::class.java)
    }
}