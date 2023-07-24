package com.jakub.data.repositories

import com.jakub.data.services.HomeService
import com.jakub.data.util.expectedListing
import com.jakub.data.util.listing
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class TechNewsRepositoryImplTest {

    @RelaxedMockK
    private lateinit var homeService: HomeService

    private lateinit var techNewsRepository: TechNewsRepositoryImpl

    @Before
    fun init() {
        MockKAnnotations.init(this)
        techNewsRepository = TechNewsRepositoryImpl(homeService)
    }

    @Test
    fun getTechNews_whenSuccess_returnsSuccess() = runTest {
        // Arrange
        val expectedResponse = expectedListing
        coEvery { homeService.getLatestTechnologyNews() } returns Response.success(listing)

        // Act
        val actualResponse = techNewsRepository.getTechNews()

        // Assert
        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun getTechNews_whenFailure_returnsError() = runTest {
        // Arrange
        val errorMessage = "ERROR_MESSAGE"
        coEvery { homeService.getLatestTechnologyNews() } throws RuntimeException(errorMessage)

        // Act
        val actualResponse = techNewsRepository.getTechNews()

        // Assert
        assertThat(actualResponse, instanceOf(ResultResponse.Error::class.java))
        assertThat(
            (actualResponse as ResultResponse.Error).error,
            instanceOf(ErrorEntity.Unknown::class.java)
        )
    }
}