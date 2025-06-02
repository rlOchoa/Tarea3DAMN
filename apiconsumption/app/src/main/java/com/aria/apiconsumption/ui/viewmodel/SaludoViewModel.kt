package com.aria.apiconsumption.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aria.apiconsumption.data.model.SaludoResponse
import com.aria.apiconsumption.data.repository.SaludoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SaludoUiState {
    object Loading : SaludoUiState()
    data class Success(val data: SaludoResponse) : SaludoUiState()
    data class Error(val message: String) : SaludoUiState()
}

class SaludoViewModel(
    private val repository: SaludoRepository = SaludoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<SaludoUiState>(SaludoUiState.Loading)
    val uiState: StateFlow<SaludoUiState> = _uiState

    init {
        obtenerSaludo()
    }

    fun obtenerSaludo() {
        _uiState.value = SaludoUiState.Loading
        viewModelScope.launch {
            val result = repository.obtenerSaludo()
            _uiState.value = result.fold(
                onSuccess = { SaludoUiState.Success(it) },
                onFailure = { SaludoUiState.Error("Error: ${it.message}") }
            )
        }
    }
}