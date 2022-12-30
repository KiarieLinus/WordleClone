package dev.kiarielinus.wordleclone.data.remote.response.dictionary


import com.google.gson.annotations.SerializedName

data class DictionaryDto(
    @SerializedName("fl")
    val functionalLabel: String
)