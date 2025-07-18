package com.watsidev.producto3.ui.screens.recipe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState

    init {
        val recipesList = recipesList
        _uiState.value = RecipeUiState(recipes = recipesList)
    }

    fun toggleFavorite(recipe: Recipe) {
        _uiState.value = _uiState.value.copy(
            recipes = _uiState.value.recipes.map {
                if (it.id == recipe.id) it.copy(isFavorite = !it.isFavorite) else it
            }
        )
    }

    fun getFavorites(): List<Recipe> {
        return _uiState.value.recipes.filter { it.isFavorite }
    }
}
