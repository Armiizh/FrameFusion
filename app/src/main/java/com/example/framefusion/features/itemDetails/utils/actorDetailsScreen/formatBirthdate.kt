package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun formatBirthdate(isoDate: String?): String {
    if (isoDate.isNullOrEmpty()) return ""

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val date = LocalDate.parse(isoDate, formatter)

            "${date.dayOfMonth.toString().padStart(2, '0')}." +
                    "${date.monthValue.toString().padStart(2, '0')}." +
                    date.year
        } catch (e: DateTimeParseException) {
            "error: ${e.message}" // Возвращаем пустую строку при ошибке парсинга
        }
    } else {
        "Ошибка в функции formatBirthdate"
    }
}
