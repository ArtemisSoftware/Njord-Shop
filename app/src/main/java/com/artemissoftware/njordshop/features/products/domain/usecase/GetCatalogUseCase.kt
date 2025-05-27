package com.artemissoftware.njordshop.features.products.domain.usecase

import com.artemissoftware.njordshop.features.products.domain.repository.ProductsRepository
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(private val productsRepository: ProductsRepository) {
    operator fun invoke() = productsRepository.getCatalog()
}