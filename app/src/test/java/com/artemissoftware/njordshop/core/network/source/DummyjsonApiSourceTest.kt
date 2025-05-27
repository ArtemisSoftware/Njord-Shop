package com.artemissoftware.njordshop.core.network.source

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.njordshop.util.data.DtoTestData
import com.artemissoftware.njordshop.util.fake.FakeDummyjsonApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DummyjsonApiSourceTest {

    private lateinit var fakeDummyjsonApi: FakeDummyjsonApi
    private lateinit var dummyjsonApiSource: DummyjsonApiSource

    @BeforeEach
    fun setUp() {
        fakeDummyjsonApi = FakeDummyjsonApi()
        dummyjsonApiSource = DummyjsonApiSource(fakeDummyjsonApi)
    }

    @Test
    fun `Get catalog with valid parameters`() = runBlocking {
        val result = dummyjsonApiSource.getProducts(limit = 10, skip = 0)

        assertThat(result)
            .isEqualTo(DtoTestData.catalogDto)
    }

    @Test
    fun `Get catalog should throw error when API fails`() = runTest {

        fakeDummyjsonApi.shouldThrowError = true

        assertFailure {
            dummyjsonApiSource.getProducts(limit = 10, skip = 0)
        }
    }
}