package com.example.framefusion.features.greeting.domain.usecases

import com.example.framefusion.features.greeting.data.local.model.UserGenres
import com.example.framefusion.features.greeting.data.repository.GenresRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class InsertGenresUseCaseTest {

    private val testRepository = mock<GenresRepository>()

    @Test
    @DisplayName("InsertGenresUseCase insert data is success")
    fun `InsertGenresUseCase insert is success`() = runTest {

        // Arrange
        val data = UserGenres(
            id = 1,
            genres = "Комедия,Ужасы,Фантастика"
        )
        val useCase = InsertGenresUseCase(testRepository)

        // Act
        useCase.invoke(data)

        //Assert
        Mockito.verify(testRepository).insertGenres(data)
    }
}