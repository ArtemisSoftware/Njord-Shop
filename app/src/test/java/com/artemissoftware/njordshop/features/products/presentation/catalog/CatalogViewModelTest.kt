package com.artemissoftware.njordshop.features.products.presentation.catalog

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import com.artemissoftware.njordshop.features.products.domain.usecase.GetCatalogUseCase
import com.artemissoftware.njordshop.features.products.domain.usecase.PreloadCatalogUseCase
import com.artemissoftware.njordshop.features.products.domain.usecase.SearchProductUseCase
import com.artemissoftware.njordshop.util.extensions.MainCoroutineExtension
import com.artemissoftware.njordshop.util.fake.FakeProductsRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class CatalogViewModelTest {

    private lateinit var productRepository: FakeProductsRepository

    private lateinit var getCatalogUseCase: GetCatalogUseCase
    private lateinit var preloadCatalogUseCase: PreloadCatalogUseCase
    private lateinit var searchProductUseCase: SearchProductUseCase

    private lateinit var viewModel: CatalogViewModel

    @BeforeEach
    fun setUp() {

        productRepository = FakeProductsRepository()

        getCatalogUseCase = GetCatalogUseCase(productsRepository = productRepository)
        preloadCatalogUseCase = PreloadCatalogUseCase(productsRepository = productRepository)
        searchProductUseCase = SearchProductUseCase(productsRepository = productRepository)

        viewModel = CatalogViewModel(
            getCatalogUseCase = getCatalogUseCase,
            preloadCatalogUseCase = preloadCatalogUseCase,
            searchProductUseCase = searchProductUseCase,
        )
    }

    @Test
    fun `Activate search when ActivateSearch is triggered`() = runTest {

        viewModel.state.test {
            viewModel.onTriggerEvent(CatalogEvent.ActivateSearch(isActive = true))

            val item1 = awaitItem()
            val item2 = awaitItem()
            assertTrue(item2.isSearching)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Update SearchQuery is triggered with value check if searchQuery has new value`() = runTest {

        val searchQuery = "gundam"

        viewModel.state.test {
            viewModel.onTriggerEvent(CatalogEvent.UpdateSearchQuery(searchQuery = searchQuery))

            val item1 = awaitItem()
            val item2 = awaitItem()
            assertThat(item2.searchQuery).isEqualTo(searchQuery)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when ClearSearch is triggered check if searchQuery and isSearching are cleared`() = runTest {

        viewModel.state.test {
            viewModel.onTriggerEvent(CatalogEvent.ActivateSearch(isActive = true))

            awaitItem()

            val item2 = awaitItem()
            assertTrue(item2.isSearching)

            viewModel.onTriggerEvent(CatalogEvent.UpdateSearchQuery(searchQuery = "gundam"))
            val item3 = awaitItem()
            assertThat(item3.searchQuery).isEqualTo("gundam")

            viewModel.onTriggerEvent(CatalogEvent.ClearSearch)
            val item4 = awaitItem()
            assertFalse(item4.isSearching)
            assertThat(item4.searchQuery).isEmpty()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when Search is triggered check searchResult is updated`() = runTest {
        viewModel.state.test {
            viewModel.onTriggerEvent(CatalogEvent.UpdateSearchQuery(searchQuery = "gundam"))
            awaitItem()
            val item2 = awaitItem()

            viewModel.onTriggerEvent(CatalogEvent.Search)

            val item3 = awaitItem()
            val item4 = awaitItem()


            assertThat(item4.searchResult).isNotEmpty()
        }
    }
}