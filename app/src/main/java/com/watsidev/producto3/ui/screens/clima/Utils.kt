package com.watsidev.producto3.ui.screens.clima

import android.util.Log
import com.watsidev.producto3.R

fun mapWeatherCodeToIcon(code: Int): Int {
    return when (code) {
        0 -> R.drawable.sun
        1 -> R.drawable.cloudy
        else -> R.drawable.cloudy
    }
}

fun mapWeatherCodeToDescription(code: Int): String {
    return when (code) {
        0 -> "Soleado"
        1 -> "Nublado"
        else -> "Lluvia"
    }
}

fun formatHour(time: String?): String { // 1. Considera hacer el input nullable
    // 2. Verifica si la cadena es null o no tiene la longitud esperada
    if (time.isNullOrBlank() || time.length < 16) {
        Log.w(
            "FormatUtils",
            "La cadena 'time' es inválida o demasiado corta para formatear la hora: '$time'"
        )
        return "HH:MM" // 3. Devuelve un valor predeterminado o maneja el error como prefieras
        // Podrías devolver "", o null si el tipo de retorno también es nullable, o lanzar una excepción personalizada.
    }
    return try {
        time.substring(11, 16)
    } catch (e: StringIndexOutOfBoundsException) {
        // 4. Captura la excepción como una última línea de defensa (aunque el if de arriba debería prevenirlo)
        Log.e("FormatUtils", "Error inesperado al formatear la hora para: '$time'", e)
        "Error" // O un valor predeterminado
    }
}

// Asumiendo que 'date' es una propiedad de ForecastItem
// y también podría ser inválida
fun ForecastItem.getFormattedDay(): String {
    if (this.date.isBlank() || this.date.length < 10) {
        Log.w(
            "FormatUtils",
            "La cadena 'date' en ForecastItem es inválida o demasiado corta: '${this.date}'"
        )
        return "YYYY-MM-DD" // O un valor predeterminado apropiado
    }
    return try {
        this.date.substring(0, 10)
    } catch (e: StringIndexOutOfBoundsException) {
        Log.e(
            "FormatUtils",
            "Error inesperado al formatear el día para ForecastItem con fecha: '${this.date}'",
            e
        )
        "Error Fecha" // O un valor predeterminado
    }
}
