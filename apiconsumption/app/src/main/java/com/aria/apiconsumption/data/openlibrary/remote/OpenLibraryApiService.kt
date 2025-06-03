package com.aria.apiconsumption.data.openlibrary.remote

import com.aria.apiconsumption.data.openlibrary.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApiService {
    @GET("search.json")
    suspend fun searchBooks(@Query("q") query: String): BookSearchResponse
}