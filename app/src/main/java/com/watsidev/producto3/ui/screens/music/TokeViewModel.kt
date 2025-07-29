package com.watsidev.producto3.ui.screens.music

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watsidev.producto3.model.data.music.SpotifyAlbum
import com.watsidev.producto3.model.data.music.SpotifyAlbumDetail
import com.watsidev.producto3.model.data.music.SpotifyArtist
import com.watsidev.producto3.model.data.music.SpotifyPlaylist
import com.watsidev.producto3.model.network.SpotifyAuthService
import com.watsidev.producto3.model.network.SpotifyService
import kotlinx.coroutines.launch
import java.util.Base64
private val clientId = "a4ef67883e934b4687b657647ac0c3f9"
private val clientSecret = "c904625e3521475297af3580bc1bf988"

@RequiresApi(Build.VERSION_CODES.O)
class SpotifyViewModel : ViewModel() {

    // ===== TOKEN =====
    var token by mutableStateOf<String?>(null)
        private set

    // ===== HOME DATA =====
    var popularArtists by mutableStateOf<List<SpotifyArtist>>(emptyList())
        private set
    var newAlbums by mutableStateOf<List<SpotifyAlbum>>(emptyList())
        private set
    var featuredPlaylists by mutableStateOf<List<SpotifyPlaylist>>(emptyList())
        private set

    // ===== ARTIST DETAIL =====
    var selectedArtist by mutableStateOf<SpotifyArtist?>(null)
        private set
    var artistAlbums by mutableStateOf<List<SpotifyAlbum>>(emptyList())
        private set

    // ===== ALBUM DETAIL =====
    var selectedAlbum by mutableStateOf<SpotifyAlbumDetail?>(null)
        private set

    // ===== UI STATES =====
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchAccessToken()
    }

    // ===========================
    // 1. Obtener Token
    // ===========================
    private fun fetchAccessToken() {
        viewModelScope.launch {
            try {
                isLoading = true
                val credentials = "$clientId:$clientSecret"
                val encoded = Base64.getEncoder().encodeToString(credentials.toByteArray())

                val response = SpotifyAuthService.api.getToken("Basic $encoded")

                if (response.isSuccessful) {
                    token = response.body()?.access_token
                    loadHomeContent()
                } else {
                    errorMessage = "Error obteniendo token: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error de red: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    // ===========================
    // 2. Cargar contenido inicial
    // ===========================
    private fun loadHomeContent() {
        searchPopularArtists("pop") // artistas más escuchados
        getNewReleases()           // álbumes nuevos
        getFeaturedPlaylists()     // playlists destacadas
    }

    // ===========================
    // 3. Popular Artists
    // ===========================
    fun searchPopularArtists(query: String) {
        viewModelScope.launch {
            token?.let {
                isLoading = true
                try {
                    val response = SpotifyService.api.search(
                        query = query,
                        type = "artist",
                        token = "Bearer $it"
                    )
                    if (response.isSuccessful) {
                        popularArtists = response.body()?.artists?.items ?: emptyList()
                    } else {
                        errorMessage = "Error cargando artistas"
                    }
                } finally {
                    isLoading = false
                }
            }
        }
    }

    // ===========================
    // 4. New Releases
    // ===========================
    fun getNewReleases() {
        viewModelScope.launch {
            token?.let {
                val response = SpotifyService.api.getNewReleases("Bearer $it")
                if (response.isSuccessful) {
                    newAlbums = response.body()?.albums?.items ?: emptyList()
                }
            }
        }
    }

    // ===========================
    // 5. Featured Playlists
    // ===========================
    fun getFeaturedPlaylists() {
        viewModelScope.launch {
            token?.let {
                val response = SpotifyService.api.getFeaturedPlaylists("Bearer $it")
                if (response.isSuccessful) {
                    featuredPlaylists = response.body()?.playlists?.items ?: emptyList()
                }
            }
        }
    }

    // ===========================
    // 6. Artist Detail
    // ===========================
    fun loadArtistDetail(artistId: String) {
        viewModelScope.launch {
            token?.let {
                isLoading = true
                try {
                    // Artista
                    val artistResponse = SpotifyService.api.getArtist(artistId, "Bearer $it")
                    if (artistResponse.isSuccessful) {
                        selectedArtist = artistResponse.body()
                    }

                    // Álbumes del artista
                    val albumsResponse = SpotifyService.api.getArtistAlbums(artistId, "Bearer $it")
                    if (albumsResponse.isSuccessful) {
                        artistAlbums = albumsResponse.body()?.items ?: emptyList()
                    }
                } finally {
                    isLoading = false
                }
            }
        }
    }

    // ===========================
    // 7. Album Detail
    // ===========================
    fun loadAlbumDetail(albumId: String) {
        viewModelScope.launch {
            token?.let {
                isLoading = true
                try {
                    val response = SpotifyService.api.getAlbum(albumId, "Bearer $it")
                    if (response.isSuccessful) {
                        selectedAlbum = response.body()
                    }
                } finally {
                    isLoading = false
                }
            }
        }
    }
}
