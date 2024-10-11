package com.example.framefusion.itemDetails.utils

fun minutesToHoursAndMinutes(minutes: Int?): String {
    if (minutes != null) {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return "${hours}ч ${remainingMinutes}мин"
    } else {
        return ""
    }
}