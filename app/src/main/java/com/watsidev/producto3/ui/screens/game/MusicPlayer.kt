package com.watsidev.producto3.ui.screens.game

import android.content.Context
import android.media.MediaPlayer
import com.watsidev.producto3.R

object MusicPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun gameMusic(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.game_music)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    fun playMainMenu(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.main_menu)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
