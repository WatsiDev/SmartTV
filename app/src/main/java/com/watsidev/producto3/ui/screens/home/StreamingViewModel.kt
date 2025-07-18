package com.watsidev.producto3.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watsidev.producto3.model.data.Movie
import com.watsidev.producto3.model.network.MovieApiClient
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    var popular by mutableStateOf<List<Movie>>(emptyList())
    var topRated by mutableStateOf<List<Movie>>(emptyList())
    var upcoming by mutableStateOf<List<Movie>>(emptyList())
    var nowPlaying by mutableStateOf<List<Movie>>(emptyList())

    var searchResults by mutableStateOf<List<Movie>>(emptyList())
        private set

    var isSearching by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(true)
        private set

    private val apiKey = "3b6b44937b4e8eaeb84ace22222a0d69"

    init {
        fetchAll()
    }

    private fun fetchAll() {
        viewModelScope.launch {
            isLoading = true
            try {
                val service = MovieApiClient.apiService
                popular = service.getPopularMovies(apiKey).results
                topRated = service.getTopRatedMovies(apiKey).results
                upcoming = service.getUpcomingMovies(apiKey).results
                nowPlaying = service.getNowPlayingMovies(apiKey).results
            } catch (e: Exception) {
                Log.e("MovieViewModel", "API Error", e)
            } finally {
                isLoading = false
            }
        }
    }

    var selectedMovie by mutableStateOf<Movie?>(null)
        private set

    fun fetchMovieById(movieId: Int) {
        viewModelScope.launch {
            try {
                val service = MovieApiClient.apiService
                selectedMovie = service.getMovieDetails(movieId, apiKey)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movie by ID", e)
            }
        }
    }

    fun searchMovies(query: String) {
        if (query.isBlank()) {
            searchResults = emptyList()
            isSearching = false
            return
        }

        viewModelScope.launch {
            try {
                isSearching = true
                val response = MovieApiClient.apiService.searchMovies(apiKey, query)
                searchResults = response.results
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Search Error", e)
                searchResults = emptyList()
            } finally {
                isSearching = false
            }
        }
    }
}
