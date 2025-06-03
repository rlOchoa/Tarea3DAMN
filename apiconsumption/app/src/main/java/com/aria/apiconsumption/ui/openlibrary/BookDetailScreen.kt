package com.aria.apiconsumption.ui.openlibrary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    title: String?,
    author: String?,
    coverId: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle del Libro") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de portada o placeholder
            if (!coverId.isNullOrBlank() && coverId != "null") {
                val imageUrl = "https://covers.openlibrary.org/b/id/$coverId-L.jpg"
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Portada del libro",
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Sin imagen", style = MaterialTheme.typography.labelLarge)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Información del libro
            Text(
                text = title ?: "Sin título",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Autor: ${author ?: "Desconocido"}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
