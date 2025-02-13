package com.example.framefusion.utils.state

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data object Loading : Result<Nothing>()
    data class Error(val error: AppError) : Result<Nothing>()
}