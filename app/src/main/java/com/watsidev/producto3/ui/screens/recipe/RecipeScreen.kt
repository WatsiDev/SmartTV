package com.watsidev.producto3.ui.screens.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.Text

@Composable
fun RecipeHomeScreen(
    viewModel: RecipeViewModel = viewModel(),
    onClick: (Any) -> Unit,
    onGoRecipe: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val recipes = uiState.recipes

    Row(modifier = Modifier.fillMaxSize()) {
        SideNavigationBar(onClick = { onClick(it) })

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(32.dp)
        ) {
            Text(
                text = "Recetas destacadas",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 220.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(recipes) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                        onGoRecipe = { onGoRecipe(it) }
                    )
                }
            }
        }
    }
}
