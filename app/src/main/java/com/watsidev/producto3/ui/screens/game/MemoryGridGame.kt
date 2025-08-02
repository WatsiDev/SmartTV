package com.watsidev.producto3.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.MaterialTheme

@Composable
fun MemoryGridGame(
    onExit: () -> Unit,
    viewModel: MemoryGridViewModel = viewModel(factory = MyViewModelFactory(LocalContext.current))
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D1B2A), Color(0xFF1B263B))
                )
            )
            .padding(16.dp)
            .focusable()
            .onKeyEvent { keyEvent ->
                // Manejo de teclas
                when (keyEvent.key) {
                    Key.DirectionUp -> if (state.currentFocus >= state.gridSize)
                        viewModel.handleUserInput(state.currentFocus - state.gridSize)

                    Key.DirectionDown -> if (state.currentFocus < state.gridSize * (state.gridSize - 1))
                        viewModel.handleUserInput(state.currentFocus + state.gridSize)

                    Key.DirectionLeft -> if (state.currentFocus % state.gridSize > 0)
                        viewModel.handleUserInput(state.currentFocus - 1)

                    Key.DirectionRight -> if (state.currentFocus % state.gridSize < state.gridSize - 1)
                        viewModel.handleUserInput(state.currentFocus + 1)

                    Key.Enter, Key.NumPadEnter, Key.DirectionCenter ->
                        viewModel.handleUserInput(state.currentFocus)
                }
                true
            }
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                Icon(
                    Icons.Outlined.Pause,
                    contentDescription = "Pause Game",
                    tint = Color.Cyan,
                    modifier = Modifier
                        .size(64.dp)
                        .weight(1f)
                        .clickable{ viewModel.togglePause() }
                )
                Text(
                    "COMMAND BEAT",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Cyan,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(16.dp))

            // Barra de progreso por secuencia
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                for (i in 0 until state.sequencesPerLevel) {
                    val color = when {
                        state.gameOver -> Color.Red
                        state.sequenceIndex > i -> Color.Green
                        else -> Color.DarkGray
                    }
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(2.dp, Color.Cyan.copy(alpha = 0.5f), CircleShape)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Mensajes de estado
            Text(
                when {
                    state.gameWon -> "WIN! - Total score: ${state.score}"
                    state.gameOver -> "YOU LOSE - Total score: ${state.score}"
                    else -> "Level ${state.currentLevel} - Score: ${state.score}"
                },
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                modifier = Modifier.shadow(1.dp)
            )

//            Text("⏱ ${state.timeLeft} s", color = Color.LightGray, fontSize = 14.sp)

            Spacer(Modifier.height(16.dp))

            // Grid de botones
            Column {
                for (row in 0 until state.gridSize) {
                    Row {
                        for (col in 0 until state.gridSize) {
                            val index = row * state.gridSize + col
                            val isHighlighted = state.highlightedIndex == index
                            val tileColor = if (isHighlighted) Color.Yellow else Color(0xFF415A77)

                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(tileColor)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Cyan,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable(
                                        enabled = !state.showSequence && !state.gameOver && !state.gameWon
                                    ) {
                                        viewModel.handleUserInput(index)
                                    }
                            )
                        }
                    }
                }
            }
        }

        // Overlays (Game Over, Ganaste, Pausa)
        if (state.gameOver || state.gameWon || state.showPauseMenu) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f))
                    .clickable(enabled = false) {}
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.gameOver) Text("GAME OVER", style = MaterialTheme.typography.bodyMedium, color = Color.Red)
                    if (state.gameWon) Text("WINNER!", style = MaterialTheme.typography.bodyMedium, color = Color.Green)
                    if (state.showPauseMenu) Text("PAUSE", style = MaterialTheme.typography.bodyMedium, color = Color.Cyan)

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = { viewModel.resetGame() }) {
                        Text(
                            "⟳ REINICIAR",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    if (state.showPauseMenu) {
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.togglePause() }) {
                            Text(
                                "▶ CONTINUAR",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { onExit() }) {
                            Text(
                                "✖ SALIR",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onDispose()
        }
    }
}
