package com.aria.apiconsumption.data.openlibrary.remote

import com.aria.apiconsumption.data.openlibrary.model.WorkDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenLibraryDetailService {
    @GET("/works/{workKey}.json")
    suspend fun getWorkDetail(@Path("workKey") workKey: String): WorkDetail
}