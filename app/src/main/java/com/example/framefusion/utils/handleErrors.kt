package com.example.framefusion.utils

import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result

// Метод для обработки ошибок
fun <T> handleErrors(code: Int): Result<T> {
    // Получаем сообщение об ошибке по коду
    val errorMessage = Constants.ErrorMessages.errorMessagesMap[code]
        ?: Constants.ErrorMessages.UNKNOWN_ERROR

    // Возвращаем результат с ошибкой
    return Result.Error(AppError.ServerError("Ошибка: $errorMessage"))
}
