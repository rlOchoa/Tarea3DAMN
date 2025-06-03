package com.aria.apiconsumption.ui.openlibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aria.apiconsumption.data.openlibrary.model.Book
import com.aria.apiconsumption.data.openlibrary.repository.OpenLibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class OpenLibraryUiState {
    object Idle : OpenLibraryUiState()
    object Loading : OpenLibraryUiState()
    object Empty : OpenLibraryUiState()
    data class Success(val books: List<Book>) : OpenLibraryUiState()
    data class Error(val message: String) : OpenLibraryUiState()
}

class OpenLibraryViewModel(
    private val repository: OpenLibraryRepository = OpenLibraryRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<OpenLibraryUiState>(OpenLibraryUiState.Idle)
    val uiState: StateFlow<OpenLibraryUiState> = _uiState

    // Cache en memoria por término de búsqueda
    private val cache = mutableMapOf<String, List<Book>>()

    private var currentQuery: String = ""

    fun searchBooks(query: String, forceRefresh: Boolean = false) {
        currentQuery = query.trim()

        // Verifica si hay resultado cacheado
        if (!forceRefresh && cache.containsKey(currentQuery)) {
            val cachedBooks = cache[currentQuery].orEmpty()
            _uiState.value = if (cachedBooks.isNotEmpty())
                OpenLibraryUiState.Success(cachedBooks)
            else
                OpenLibraryUiState.Empty
            return
        }

        _uiState.value = OpenLibraryUiState.Loading

        viewModelScope.launch {
            val result = repository.searchBooks(currentQuery)
            _uiState.value = result.fold(
                onSuccess = {
                    val books = it.docs
                    cache[currentQuery] = books // guarda en cache

                    if (books.isEmpty())
                        OpenLibraryUiState.Empty
                    else
                        OpenLibraryUiState.Success(books)
                },
                onFailure = {
                    OpenLibraryUiState.Error("Error al cargar libros: ${it.localizedMessage}")
                }
            )
        }
    }

    fun retry() {
        searchBooks(currentQuery, forceRefresh = true)
    }

    fun refresh() {
        searchBooks(currentQuery, forceRefresh = true)
    }
}