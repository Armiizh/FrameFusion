package com.example.framefusion.features.search.domain.usecases

import com.example.framefusion.features.search.data.SearchItemDatabaseRepository
import com.example.framefusion.features.search.data.SearchServiceRepository
import com.example.framefusion.features.search.data.local.models.SearchItem
import com.example.framefusion.features.search.data.rest.models.toSearchItemList
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.handleErrors
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetSearchItemUseCase @Inject constructor(
    private val searchServiceRepository: SearchServiceRepository,
    private val searchItemDatabaseRepository: SearchItemDatabaseRepository
) {
    suspend fun invoke(name: String): Result<List<SearchItem>> = withContext(Dispatchers.IO) {

        try {
            val response = searchServiceRepository.getSearchItem(
                page = 1,
                limit = 13,
                name = name
            )

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val searchItems = body.toSearchItemList()
                    searchItemDatabaseRepository.updateSearchItem(searchItems)
                    Result.Success(searchItems)
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
}