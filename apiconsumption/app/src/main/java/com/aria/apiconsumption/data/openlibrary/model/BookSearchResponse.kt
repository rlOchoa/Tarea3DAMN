package com.aria.apiconsumption.data.openlibrary.model

data class BookSearchResponse(
    val docs: List<Book> = emptyList()
)

data class Book(
    val title: String?,
    val author_name: List<String>?,
    val cover_i: Int?,
    val key: String? // 👈 necesario para obtener detalles
) {
    val coverUrl: String?
        get() = cover_i?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" }
}
