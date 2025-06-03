package com.aria.apiconsumption.ui.openlibrary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aria.apiconsumption.data.openlibrary.model.Book
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenLibraryScreen(
    navController: NavController,
    viewModel: OpenLibraryViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf(TextFieldValue("")) }
    var isGrid by remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState is OpenLibraryUiState.Loading)

    val categorias = listOf("Fantasía", "Ciencia Ficción", "Historia", "Tecnología", "Romance")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Categorías", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
                categorias.forEach { categoria ->
                    NavigationDrawerItem(
                        label = { Text(categoria) },
                        selected = false,
                        onClick = {
                            query = TextFieldValue(categoria)
                            viewModel.searchBooks(categoria)
                            scope.launch { drawerState.close() }
                        }
                    )
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                NavigationDrawerItem(
                    label = { Text(if (isGrid) "Cambiar a Lista" else "Cambiar a Grid") },
                    selected = false,
                    onClick = {
                        isGrid = !isGrid
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Open Library") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Buscar libro") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    trailingIcon = {
                        if (uiState is OpenLibraryUiState.Loading) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            IconButton(onClick = { viewModel.searchBooks(query.text) }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Buscar"
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = { viewModel.refresh() }
                ) {
                    when (uiState) {
                        is OpenLibraryUiState.Loading -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }

                        is OpenLibraryUiState.Success -> {
                            val books = (uiState as OpenLibraryUiState.Success).books
                            if (isGrid) {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(books) { book ->
                                        BookGridItem(book = book) {
                                            navController.navigate(
                                                "public/detail/${
                                                    URLEncoder.encode(book.title.orEmpty(), StandardCharsets.UTF_8.name())
                                                }/${
                                                    URLEncoder.encode(book.author_name?.firstOrNull().orEmpty(), StandardCharsets.UTF_8.name())
                                                }/${book.cover_i ?: "null"}/${
                                                    URLEncoder.encode(book.key.orEmpty(), StandardCharsets.UTF_8.name())
                                                }"
                                            )
                                        }
                                    }
                                }
                            } else {
                                LazyColumn {
                                    items(books) { book ->
                                        BookItem(book = book) {
                                            navController.navigate(
                                                "public/detail/${
                                                    URLEncoder.encode(book.title.orEmpty(), StandardCharsets.UTF_8.name())
                                                }/${
                                                    URLEncoder.encode(book.author_name?.firstOrNull().orEmpty(), StandardCharsets.UTF_8.name())
                                                }/${book.cover_i ?: "null"}/${
                                                    URLEncoder.encode(book.key.orEmpty(), StandardCharsets.UTF_8.name())
                                                }"
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        is OpenLibraryUiState.Empty -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(
                                    "No se encontraron resultados, intenta con otra búsqueda.",
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        is OpenLibraryUiState.Error -> {
                            val msg = (uiState as OpenLibraryUiState.Error).message
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = msg,
                                    color = MaterialTheme.colorScheme.error,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(onClick = { viewModel.retry() }) {
                                    Text("Reintentar")
                                }
                            }
                        }

                        OpenLibraryUiState.Idle -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Realiza una búsqueda o abre el menú para ver categorías.")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookItem(book: Book, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                if (book.coverUrl != null) {
                    Image(
                        painter = rememberAsyncImagePainter(book.coverUrl),
                        contentDescription = "Portada"
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sin imagen",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = book.title ?: "Sin título",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = book.author_name?.joinToString(", ") ?: "Autor desconocido",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun BookGridItem(book: Book, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                if (book.coverUrl != null) {
                    Image(
                        painter = rememberAsyncImagePainter(book.coverUrl),
                        contentDescription = "Portada"
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sin imagen",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.title ?: "Sin título",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2
            )
            Text(
                text = book.author_name?.joinToString(", ") ?: "Autor desconocido",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}