package com.artemissoftware.njordshop.features.products.presentation.detail

import androidx.lifecycle.SavedStateHandle
import assertk.assertThat
import assertk.assertions.isNotNull
import com.artemissoftware.njordshop.features.products.domain.usecase.GetProductUseCase
import com.artemissoftware.njordshop.util.fake.FakeProductsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getProductUseCase: GetProductUseCase
    private lateinit var productRepository: FakeProductsRepository
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: DetailViewModel

    @BeforeEach
    fun setup() {

        Dispatchers.setMain(testDispatcher)  // Set the main dispatcher to the test dispatcher

        productRepository = FakeProductsRepository()
        getProductUseCase = GetProductUseCase(productsRepository = productRepository)
        savedStateHandle = mockk(relaxed = true)

        every { savedStateHandle.get<Int>("id") } returns 1
        every { savedStateHandle.get<DetailState>("state") } returns DetailState()

        viewModel = DetailViewModel(
            getProductUseCase = getProductUseCase,
            savedStateHandle = savedStateHandle,
        )
    }

    @AfterEach
    fun teardown() {
        Dispatchers.resetMain()  // Reset after each test to avoid side effects
    }

    @Test
    fun `when Loading product from navigation is triggered and is found`() = runTest {

        advanceUntilIdle()

        assertThat(viewModel.state.value.product).isNotNull()
    }

    @Test
    fun `when Loading product from navigation is triggered and no id is found update error data`() = runTest {
        every { savedStateHandle.get<Int>("id") } returns 14

        val viewModel = DetailViewModel(
            getProductUseCase = getProductUseCase,
            savedStateHandle = savedStateHandle,
        )

        advanceUntilIdle()

        assertThat(viewModel.state.value.error?.message).isNotNull()
    }

}