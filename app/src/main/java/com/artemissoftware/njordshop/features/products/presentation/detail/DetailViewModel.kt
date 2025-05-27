package com.artemissoftware.njordshop.features.products.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.domain.error.NjordShopError
import com.artemissoftware.njordshop.core.presentation.ui.composables.text.UiText
import com.artemissoftware.njordshop.core.presentation.ui.models.ErrorData
import com.artemissoftware.njordshop.core.presentation.ui.util.extensions.toUiText
import com.artemissoftware.njordshop.features.products.domain.usecase.GetProductUseCase
import com.artemissoftware.njordshop.features.products.presentation.detail.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("id")?.let {
            getProduct(id = it)
        } ?: run {
            updateState {
                it.copy(
                    error = ErrorData(
                        message = NjordShopError.NoDetailFound.toUiText(),
                    ),
                )
            }
        }
    }

    private fun updateState(update: (DetailState) -> DetailState) {
        savedStateHandle["state"] = _state.updateAndGet { update(it) }
    }

    private fun getProduct(id: Int) = with(_state) {

        updateState {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            getProductUseCase(id = id)
                .onSuccess { product ->
                    updateState {
                        it.copy(
                            product = product.toUiModel(),
                            isLoading = false,
                            error = null
                        )
                    }
                }
                .onFailure { error ->
                    updateState {
                        it.copy(
                            product = null,
                            isLoading = false,
                            error = ErrorData(
                                message = error.toUiText(),
                                buttonText = UiText.StringResource(R.string.try_again),
                            ),
                        )
                    }
                }
        }
    }
}