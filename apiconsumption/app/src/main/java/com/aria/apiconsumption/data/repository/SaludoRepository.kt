package com.aria.apiconsumption.data.repository

import com.aria.apiconsumption.data.model.SaludoResponse
import com.aria.apiconsumption.data.remote.RetrofitClient

class SaludoRepository {
    suspend fun obtenerSaludo(): Result<SaludoResponse> {
        return try {
            val response = RetrofitClient.apiService.getSaludo()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}