package com.artemissoftware.njordshop.features.products.domain.usecase

import com.artemissoftware.njordshop.features.products.domain.repository.ProductsRepository
import javax.inject.Inject

class PreloadCatalogUseCase @Inject constructor(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke() = productsRepository.preloadAllProducts()
}