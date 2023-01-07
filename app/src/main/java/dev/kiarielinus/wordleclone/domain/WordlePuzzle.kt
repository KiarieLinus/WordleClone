package dev.kiarielinus.wordleclone.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.kiarielinus.wordleclone.domain.model.WordleCell
import java.util.LinkedList

class WordlePuzzle(private val difficulty: Int) {
    private val row = LinkedList<MutableState<WordleCell>>()
    private val graph: HashMap<Int, LinkedList<MutableState<WordleCell>>> = HashMap()

    fun buildPuzzle(): HashMap<Int, LinkedList<MutableState<WordleCell>>> {
        (0 until difficulty).forEach {
            row.add(it, mutableStateOf(WordleCell()))
        }

        (0..difficulty).forEach { graph[it] = row }

        return graph
    }
}