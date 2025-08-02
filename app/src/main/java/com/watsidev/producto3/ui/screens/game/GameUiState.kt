package com.watsidev.producto3.ui.screens.game

// MemoryGridUiState.kt
data class MemoryGridUiState(
    val audioInitialized: Boolean = false,
    val currentLevel: Int = 1,
    val sequenceIndex: Int = 0,
    val sequence: List<Int> = emptyList(),
    val userInput: List<Int> = emptyList(),
    val gridSize: Int = 3,
    val sequencesPerLevel: Int = 5,
    val showSequence: Boolean = true,
    val highlightedIndex: Int? = null,
    val currentFocus: Int = 0,
    val gameOver: Boolean = false,
    val gameWon: Boolean = false,
    val timeLeft: Int = 0,
    val showPauseMenu: Boolean = false,
    val score: Int = 0
)
