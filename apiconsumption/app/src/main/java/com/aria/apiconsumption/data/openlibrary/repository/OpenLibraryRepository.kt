package com.aria.apiconsumption.data.openlibrary.repository

import com.aria.apiconsumption.data.openlibrary.model.BookSearchResponse
import com.aria.apiconsumption.data.openlibrary.remote.OpenLibraryClient

class OpenLibraryRepository {
    suspend fun searchBooks(query: String): Result<BookSearchResponse> {
        return try {
            val result = OpenLibraryClient.api.searchBooks(query)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}