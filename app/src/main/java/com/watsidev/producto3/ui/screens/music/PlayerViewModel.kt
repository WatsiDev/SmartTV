package com.watsidev.producto3.ui.screens.music

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.watsidev.producto3.ui.screens.music.utils.getPlaylistFromSongId
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlaylist: List<MusicLocalModel> = emptyList()
    private var currentIndex: Int = 0

    val currentSong = MutableStateFlow<MusicLocalModel?>(null)
    val isPlaying = MutableStateFlow(false)
    val progress = MutableStateFlow(0f)
    val currentTime = MutableStateFlow("00:00")
    val totalTime = MutableStateFlow("00:00")

    init {
        // Actualizar progreso cada 500ms
        viewModelScope.launch {
            while (true) {
                mediaPlayer?.let { mp ->
                    if (mp.isPlaying) {
                        progress.value = mp.currentPosition.toFloat() / mp.duration
                        currentTime.value = formatTime(mp.currentPosition)
                        totalTime.value = formatTime(mp.duration)
                    }
                }
                delay(500)
            }
        }
    }

    fun playSong(songId: Int, fromFavorites: Boolean = false) {
        if (fromFavorites) {
            currentPlaylist = MusicRepository.getFavorites()
        } else {
            val (playlist, startIndex) = getPlaylistFromSongId(songId) ?: return
            currentPlaylist = playlist
            currentIndex = startIndex
        }

        // Si venía de favoritos, buscar el índice de la canción en favoritos
        if (fromFavorites) {
            currentIndex = currentPlaylist.indexOfFirst { it.id == songId }
        }

        startPlayback()
    }


    private fun startPlayback() {
        stop()
        val song = currentPlaylist[currentIndex]
        currentSong.value = song
        mediaPlayer = MediaPlayer.create(context, song.songRes).apply {
            setOnCompletionListener { next() }
            start()
        }
        isPlaying.value = true
    }

    fun playPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPlaying.value = false
            } else {
                it.start()
                isPlaying.value = true
            }
        }
    }

    fun next() {
        currentIndex = (currentIndex + 1) % currentPlaylist.size
        startPlayback()
    }

    fun previous() {
        currentIndex = if (currentIndex - 1 < 0) currentPlaylist.lastIndex else currentIndex - 1
        startPlayback()
    }

    fun toggleFavorite() {
        currentSong.value?.let {
            MusicRepository.toggleFavorite(it)
            // Emitimos de nuevo para actualizar UI
            currentSong.value = it.copy()
        }
    }

    private fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun formatTime(ms: Int): String {
        val totalSeconds = ms / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return "%02d:%02d".format(minutes, seconds)
    }
}
