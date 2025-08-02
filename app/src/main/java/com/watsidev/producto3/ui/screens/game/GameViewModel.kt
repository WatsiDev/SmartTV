package com.watsidev.producto3.ui.screens.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

// MemoryGridViewModel.kt
class MemoryGridViewModel(private val context: Context) : ViewModel() {

    private val _uiState = MutableStateFlow(MemoryGridUiState())
    val uiState: StateFlow<MemoryGridUiState> = _uiState

    private val totalGameLevels = 999

    init {
        initializeAudio()
        resetGame()
    }

    private fun initializeAudio() {
        if (!_uiState.value.audioInitialized) {
            SoundManager.initialize(context)
            MusicPlayer.gameMusic(context)
            _uiState.update { it.copy(audioInitialized = true) }
        }
    }

    fun resetGame() {
        val gridSize = calculateGridSize(1)
        _uiState.value = MemoryGridUiState(
            gridSize = gridSize,
            sequencesPerLevel = calculateSequencesPerLevel(1),
            currentFocus = (gridSize * gridSize) / 2,
            score = 0
        )
        startSequence()
    }

    private fun calculateGridSize(level: Int) = if (level > 5) 6  else 3 + ((level - 1) / 2)
    private fun calculateSequencesPerLevel(level: Int) = if (level > 5 ) 10 else 5 + ((level - 1) / 2)

    fun startSequence() {
        viewModelScope.launch {
            val state = _uiState.value

            if (state.currentLevel > totalGameLevels) {
                _uiState.update { it.copy(gameWon = true) }
                SoundManager.play("correct")
                return@launch
            }

            _uiState.update { it.copy(showSequence = true) }

            val totalCells = state.gridSize * state.gridSize
            val newStep = Random.nextInt(totalCells)

            val newSequence = if (state.sequenceIndex == 0) listOf(newStep) else state.sequence + newStep

            delay(1000)
            // Mostrar secuencia
            for (i in newSequence) {
                _uiState.update { it.copy(highlightedIndex = i) }
                SoundManager.play("tile")
                delay((1000L - (state.currentLevel - 1) * 100).coerceAtLeast(300L))
                _uiState.update { it.copy(highlightedIndex = null) }
                delay(200)
            }

            _uiState.update { it.copy(sequence = newSequence, userInput = emptyList(), showSequence = false) }

//            startTimer()
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            val timeLimit = (6 - _uiState.value.currentLevel).coerceAtLeast(2 + _uiState.value.gridSize / 2)
            _uiState.update { it.copy(timeLeft = timeLimit) }

            while (_uiState.value.timeLeft > 0 && _uiState.value.userInput.size < _uiState.value.sequence.size) {
                delay(1000)
                _uiState.update { it.copy(timeLeft = it.timeLeft - 1) }
            }

            if (_uiState.value.userInput.size < _uiState.value.sequence.size) {
                gameOver()
            }
        }
    }

    fun handleUserInput(index: Int) {
        val state = _uiState.value
        if (state.showSequence || state.gameOver || state.gameWon) return

        SoundManager.play("button")
        val updatedInput = state.userInput + index

        if (state.sequence[updatedInput.lastIndex] != index) {
            gameOver()
        } else if (updatedInput.size == state.sequence.size) {
            SoundManager.play("correct")
            val nextIndex = state.sequenceIndex + 1

            if (nextIndex >= state.sequencesPerLevel) {
                val newScore = state.score + 50
                SoundManager.play("levelUp")
                val newLevel = state.currentLevel + 1
                val newGridSize = calculateGridSize(newLevel)

                _uiState.update {
                    it.copy(
                        currentLevel = newLevel,
                        sequenceIndex = 0,
                        sequence = emptyList(),
                        gridSize = newGridSize,
                        sequencesPerLevel = calculateSequencesPerLevel(newLevel),
                        currentFocus = (newGridSize * newGridSize) / 2,
                        score = newScore
                    )
                }
            } else {
                // Puntos por completar secuencia
                val newScore = state.score + 10 // Ejemplo: +10 por secuencia
                _uiState.update {
                    it.copy(sequenceIndex = nextIndex, userInput = emptyList(), score = newScore)
                }
            }

            startSequence()
        } else {
            _uiState.update { it.copy(userInput = updatedInput) }
        }
    }

    private fun gameOver() {
        SoundManager.play("fail")
        _uiState.update { it.copy(gameOver = true) }
    }

    fun togglePause() {
        _uiState.update { it.copy(showPauseMenu = !it.showPauseMenu) }
    }

    fun onDispose() {
        MusicPlayer.stopMusic()
        SoundManager.release()
    }
}
