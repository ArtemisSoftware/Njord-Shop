package com.artemissoftware.njordshop.features.products.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.njordshop.core.domain.Resource
import com.artemissoftware.njordshop.core.network.source.DummyjsonApiSource
import com.artemissoftware.njordshop.features.products.domain.repository.ProductsRepository
import com.artemissoftware.njordshop.util.data.TestData
import com.artemissoftware.njordshop.util.fake.FakeDummyjsonApi
import com.artemissoftware.njordshop.util.fake.FakeProductDao
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductsRepositoryImplTest {

    private lateinit var productDao: FakeProductDao
    private lateinit var dummyjsonApi: FakeDummyjsonApi
    private lateinit var dummyjsonApiSource: DummyjsonApiSource
    private lateinit var productsRepository: ProductsRepository

    @BeforeEach
    fun setUp() {
        productDao = FakeProductDao()
        dummyjsonApi = FakeDummyjsonApi()
        dummyjsonApiSource = DummyjsonApiSource(dummyjsonApi)
        productsRepository = ProductsRepositoryImpl(productDao = productDao, dummyjsonApiSource = dummyjsonApiSource)
    }

    @Test
    fun `Preload data from API to DB`() = runBlocking {
        productsRepository.preloadAllProducts()

        assertThat(productDao.getCount())
            .isEqualTo(1)
    }

    @Test
    fun `Preload data from API to DB with error`() = runBlocking {
        dummyjsonApi.shouldThrowError = true

        val result = productsRepository.preloadAllProducts()

        assertThat(productDao.getCount())
            .isEqualTo(0)

        Assertions.assertTrue { result is Resource.Failure }
    }

    @Test
    fun `Get product with success`() = runBlocking {

        productsRepository.preloadAllProducts()

        val result = productsRepository.getProduct(1)

        val resource = (result as Resource.Success)
        assertThat(resource.data).isEqualTo(listOf(listOf(TestData.product)))
    }

    @Test
    fun `Get product not found`() = runBlocking {

        productsRepository.preloadAllProducts()

        val result = productsRepository.getProduct(2)

        Assertions.assertTrue { result is Resource.Failure }
    }

    @Test
    fun `Search product with success`() = runBlocking {

        productsRepository.preloadAllProducts()
        val result = productsRepository.search("Grade")

        val resource = (result as Resource.Success)
        assertThat(resource.data).isEqualTo(listOf(TestData.productEntry))

    }

    @Test
    fun `Search product not found`() = runBlocking {

        productsRepository.preloadAllProducts()
        val result = productsRepository.search("Batman")

        Assertions.assertTrue { result is Resource.Failure }
    }
}