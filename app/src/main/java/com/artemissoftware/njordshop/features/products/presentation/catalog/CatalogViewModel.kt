package com.artemissoftware.njordshop.features.products.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.ui.composables.text.UiText
import com.artemissoftware.njordshop.core.presentation.ui.models.ErrorData
import com.artemissoftware.njordshop.core.presentation.ui.util.extensions.toUiText
import com.artemissoftware.njordshop.features.products.domain.usecase.GetCatalogUseCase
import com.artemissoftware.njordshop.features.products.domain.usecase.PreloadCatalogUseCase
import com.artemissoftware.njordshop.features.products.domain.usecase.SearchProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val preloadCatalogUseCase: PreloadCatalogUseCase,
    private val searchProductUseCase: SearchProductUseCase,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(CatalogState())
    val state = _state.asStateFlow()
        .onStart {
            if (!hasLoadedInitialData) {
                preloadCatalog()
                getCatalog()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CatalogState()
        )

    fun onTriggerEvent(event: CatalogEvent) {
        when (event) {
            is CatalogEvent.ActivateSearch -> {
                activateSearch(event.isActive)
            }
            CatalogEvent.ClearSearch -> {
                clearSearch()
            }
            CatalogEvent.Search -> {
                search()
            }
            is CatalogEvent.UpdateSearchQuery -> {
                updateSearchQuery(searchQuery = event.searchQuery)
            }
        }
    }

    private fun preloadCatalog(forceReload: Boolean = false) = with(_state) {

        update {
            it.copy(isLoading = true, error = null)
        }

        viewModelScope.launch {
            preloadCatalogUseCase()
                .onSuccess {
                    if(forceReload) {
                        getCatalog()
                    }
                    update {
                        it.copy(isLoading = false, error = null)
                    }
                }
                .onFailure { error ->
                    update {
                        it.copy(
                            isLoading = false,
                            error = ErrorData(
                                message = error.toUiText(),
                                buttonText = UiText.StringResource(R.string.try_again),
                                onClick = {
                                    reloadPreloadCatalog()
                                }
                            ),
                        )
                    }
                }
        }
    }

    private fun reloadPreloadCatalog(){
        preloadCatalog(true)
    }

    private fun getCatalog() = with(_state) {
        val products = getCatalogUseCase()
            .cachedIn(viewModelScope)

        update {
            it.copy(products = products)
        }
    }

    private fun search() = with(_state) {
        updateStartSearch()

        viewModelScope.launch {
            searchProductUseCase(query = value.searchQuery)
                .onSuccess { products ->
                    update {
                        it.copy(
                            searchResult = products,
                            isLoading = false,
                            error = null,
                            isSearching = false
                        )
                    }
                }
                .onFailure { error ->
                    update {
                        it.copy(
                            searchResult = emptyList(),
                            isLoading = false,
                            isSearching = false,
                            error = ErrorData(
                                message = error.toUiText()
                            ),
                        )
                    }
                }
        }
    }

    private fun updateSearchQuery(searchQuery: String) = with(_state) {
        update {
            it.copy(searchQuery = searchQuery)
        }
    }

    private fun activateSearch(isActive: Boolean) = with(_state) {
        update {
            it.copy(isSearching = isActive)
        }
    }

    private fun clearSearch() = with(_state) {
        update {
            it.copy(isSearching = false, searchQuery = "", error = null)
        }
    }

    private fun updateStartSearch() = with(_state) {
        update {
            it.copy(isLoading = true, error = null)
        }
    }
}