package com.aria.apiconsumption.data.openlibrary.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenLibraryDetailClient {
    val service: OpenLibraryDetailService by lazy {
        Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenLibraryDetailService::class.java)
    }
}