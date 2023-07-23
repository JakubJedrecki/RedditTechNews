package com.jakub.reddittechnews.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakub.domain.models.Post
import com.jakub.domain.repositories.TechNewsRepository
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var repository: TechNewsRepository

    lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `refresh should clear ui list`() {
        val expectedUiState = HomeUiState()

        viewModel.getLatestNews()

        assertEquals(expectedUiState, viewModel.uiState.value)
    }

    @Test
    fun `refresh should get tech news from repository`() = runTest {
        val expectedPosts = listOf(
            Post(
                title = "Post 1",
                author = "author 1",
                timestamp = "1",
                imageUrl = "",
                linkFlairText = "label"
            ),
            Post(
                title = "Post 2",
                author = "author 2",
                timestamp = "2",
                imageUrl = "",
                linkFlairText = "label 2"
            ),
        )
        coEvery { repository.getTechNews() } returns ResultResponse.Success(expectedPosts)

        viewModel.getLatestNews()

        val actualUiState = viewModel.uiState.value
        assertEquals(expectedPosts, actualUiState.posts)
    }

    @Test
    fun `refresh should show error if get tech news fails`() = runTest {
        val expectedError = "network failure"
        coEvery { repository.getTechNews() } returns ResultResponse.Error(ErrorEntity.NetworkFailure())

        viewModel.getLatestNews()

        val actualUiState = viewModel.uiState.value
        assertEquals(expectedError, actualUiState.errorMsg)
    }
}