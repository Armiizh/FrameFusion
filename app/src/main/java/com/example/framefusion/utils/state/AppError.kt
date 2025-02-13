package com.example.framefusion.utils.state

sealed class AppError(open val message: String) {
    data class NetworkError(
        override val message: String,
        val code: Int? = null
    ) : AppError(message)

    data class ServerError(
        override val message: String,
        val serverMessage: String? = null
    ) : AppError(message)

    data class DatabaseError(
        override val message: String
    ) : AppError(message)

    data class UnknownError(
        override val message: String
    ) : AppError(message)

    // Метод для получения локализованного сообщения
    fun getLocalizedMessage(): String = when (this) {
        is NetworkError -> when {
            code == 404 -> "Запрошенный ресурс не найден"
            code == 500 -> "Внутренняя ошибка сервера"
            code != null -> "Ошибка сети (код $code)"
            else -> "Проблема с подключением к интернету"
        }

        is ServerError -> serverMessage ?: "Ошибка сервера"
        is DatabaseError -> "Ошибка базы данных"
        is UnknownError -> "Непредвиденная ошибка"
    }

    override fun toString(): String = message
}