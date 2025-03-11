package com.example.framefusion.features.greeting.domain.usecases

import com.example.framefusion.features.greeting.data.GenresRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetGenresUseCaseTest {

    private val testRepository = mock<GenresRepository>()

    @Test
    fun `test GetGenresUseCase returns correct data`() = runTest {

        // Arrange
        val data = "Комедия,Ужасы,Фантастика"
        Mockito.`when`(testRepository.getGenres()).thenReturn(data)
        val useCase = GetGenresUseCase(testRepository)

        // Act
        val result = useCase.invoke()

        // Assert
        assertEquals(data, result)
    }
}