package com.aria.apiconsumption.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aria.apiconsumption.ui.viewmodel.SaludoUiState
import com.aria.apiconsumption.ui.viewmodel.SaludoViewModel

@Composable
fun MainScreen(viewModel: SaludoViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(16.dp)) {
            when (uiState) {
                is SaludoUiState.Loading -> CircularProgressIndicator()
                is SaludoUiState.Success -> {
                    val mensaje = (uiState as SaludoUiState.Success).data.mensaje
                    Text(text = mensaje, style = MaterialTheme.typography.headlineMedium)
                }
                is SaludoUiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = (uiState as SaludoUiState.Error).message,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.obtenerSaludo() }) {
                            Text("Reintentar")
                        }
                    }
                }
            }
        }
    }
}