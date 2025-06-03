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
    data class Success(val books: List<Book>) : OpenLibraryUiState()
    data class Error(val message: String) : OpenLibraryUiState()
}

class OpenLibraryViewModel(
    private val repository: OpenLibraryRepository = OpenLibraryRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<OpenLibraryUiState>(OpenLibraryUiState.Idle)
    val uiState: StateFlow<OpenLibraryUiState> = _uiState

    fun searchBooks(query: String) {
        _uiState.value = OpenLibraryUiState.Loading
        viewModelScope.launch {
            val result = repository.searchBooks(query)
            _uiState.value = result.fold(
                onSuccess = { OpenLibraryUiState.Success(it.docs) },
                onFailure = { OpenLibraryUiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}