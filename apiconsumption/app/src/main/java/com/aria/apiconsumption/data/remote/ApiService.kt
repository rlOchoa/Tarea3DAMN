package com.aria.apiconsumption.data.remote

import com.aria.apiconsumption.data.model.SaludoResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/api/saludo")
    suspend fun getSaludo(): SaludoResponse
}