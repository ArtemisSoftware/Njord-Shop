package com.artemissoftware.njordshop.features.products.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.njordshop.util.data.DtoTestData.productDto
import com.artemissoftware.njordshop.util.data.EntityTestData.productEntity
import com.artemissoftware.njordshop.util.data.TestData.product
import com.artemissoftware.njordshop.util.data.TestData.productEntry
import org.junit.jupiter.api.Test

class ProductMapperTest {

    @Test
    fun `Map ProductDto to ProductEntity`() {
        assertThat(productDto.toEntity())
            .isEqualTo(productEntity)
    }

    @Test
    fun `Map ProductEntity to Product`() {
        assertThat(productEntity.toProduct())
            .isEqualTo(product)
    }

    @Test
    fun `Map ProductEntity to ProductEntry`() {
        assertThat(productEntity.toProductEntry())
            .isEqualTo(productEntry)
    }
}