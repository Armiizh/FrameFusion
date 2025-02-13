package com.example.framefusion.features.search.domain.usecases

import com.example.framefusion.features.search.data.SearchItemDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteSearchBarUseCase @Inject constructor(private val searchItemDatabaseRepository: SearchItemDatabaseRepository) {

    suspend fun invoke() = withContext(Dispatchers.IO) {
        searchItemDatabaseRepository.deleteSearchItem()
    }
}