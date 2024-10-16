package com.example.framefusion.itemDetails.utils.converters

fun minutesToHoursAndMinutes(minutes: Int?): String {
    if (minutes != null) {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        return if (hours > 0) {
            if (remainingMinutes > 0) {
                "${hours}ч ${remainingMinutes}мин"
            } else {
                "${hours}ч"
            }
        } else {
            "${remainingMinutes}мин"
        }
    } else {
        return ""
    }
}