package com.watsidev.producto3.ui.screens.game

import android.content.Context
import android.media.SoundPool
import com.watsidev.producto3.R

object SoundManager {
    private var soundPool: SoundPool? = null
    private val soundMap = mutableMapOf<String, Int>()

    fun initialize(context: Context) {
        soundPool = SoundPool.Builder().setMaxStreams(5).build()
        soundMap["button"] = soundPool?.load(context, R.raw.snd_mtt_prebomb, 1) ?: 0
        soundMap["tile"] = soundPool?.load(context, R.raw.snd_mtt_burst, 1) ?: 0
        soundMap["correct"] = soundPool?.load(context, R.raw.snd_item, 1) ?: 0
        soundMap["fail"] = soundPool?.load(context, R.raw.snd_mtt_hit, 1) ?: 0
        soundMap["levelUp"] = soundPool?.load(context, R.raw.snd_levelup, 1) ?: 0
    }

    fun play(name: String) {
        soundMap[name]?.let { id ->
            soundPool?.play(id, 1f, 1f, 1, 0, 1f)
        }
    }

    fun release() {
        soundPool?.release()
        soundPool = null
    }
}
