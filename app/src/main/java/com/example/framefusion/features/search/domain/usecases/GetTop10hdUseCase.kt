package com.example.framefusion.features.search.domain.usecases

import com.example.framefusion.features.search.data.SearchServiceRepository
import com.example.framefusion.features.search.data.Top10hdDatabaseRepository
import com.example.framefusion.features.search.data.local.models.Top10hd
import com.example.framefusion.features.search.data.rest.models.toTop10hdItemList
import com.example.framefusion.features.search.utils.FieldsForSearchScreenUseCases.Top10hd.notNullFields
import com.example.framefusion.features.search.utils.FieldsForSearchScreenUseCases.Top10hd.selectedFields
import com.example.framefusion.features.search.utils.FieldsForSearchScreenUseCases.Top10hd.sortOrder
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.handleErrors
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetTop10hdUseCase @Inject constructor(
    private val searchServiceRepository: SearchServiceRepository,
    private val top10hdDatabaseRepository: Top10hdDatabaseRepository
) {
    suspend fun invoke(forceRefresh: Boolean = false): Result<List<Top10hd>> =
        withContext(Dispatchers.IO) {

            try {

                if (!forceRefresh) {
                    val data = top10hdDatabaseRepository.getTop10hd()
                    if (data.isNotEmpty()) {
                        return@withContext Result.Success(data)
                    }
                }

                val response = searchServiceRepository.getTop10hd(
                    page = 1,
                    limit = 10,
                    selectedFields = selectedFields,
                    notNullFields = notNullFields,
                    lists = "top10-hd"
                )

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val top10hd = body.toTop10hdItemList()
                        val orderMap = sortOrder.withIndex().associate { it.value to it.index }
                        val sortedTop10hd = top10hd
                            .sortedWith(compareBy { orderMap[it.name] ?: Int.MAX_VALUE })
                            .mapIndexed { index, item ->
                                item.copy(displayId = index + 1)
                            }
                        top10hdDatabaseRepository.updateSearchItem(sortedTop10hd)
                        Result.Success(sortedTop10hd)
                    } else {
                        Result.Error(
                            AppError.NetworkError(
                                Constants.ErrorMessages.EMPTY_RESPONSE,
                                response.code()
                            )
                        )
                    }
                } else {
                    handleErrors(response.code())
                }
            } catch (e: IOException) {
                Result.Error(AppError.NetworkError(Constants.ErrorMessages.NETWORK_ERROR))
            } catch (e: Exception) {
                Result.Error(
                    AppError.UnknownError(
                        e.localizedMessage ?: Constants.ErrorMessages.UNKNOWN_ERROR
                    )
                )
            }
        }

    // Функция для проверки совпадения sortOrder
    private fun isSortOrderMatching(data: List<Top10hd>, sortOrder: List<String>): Boolean {
        // Здесь вы можете реализовать логику сравнения
        // Например, если у вас есть поле name в Top10hd, вы можете сравнить его с sortOrder
        val currentOrder = data.map { it.name } // Предполагаем, что у Top10hd есть поле name
        return currentOrder == sortOrder
    }
}