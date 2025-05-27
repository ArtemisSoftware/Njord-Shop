package com.artemissoftware.njordshop.features.products.domain.usecase

import com.artemissoftware.njordshop.features.products.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(id: Int) = productsRepository.getProduct(id = id)
}