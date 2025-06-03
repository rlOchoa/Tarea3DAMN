package com.aria.apiconsumption.data.openlibrary.model

data class WorkDetail(
    val description: Any? // Puede ser String o un objeto con propiedad "value"
) {
    val resolvedDescription: String
        get() = when (description) {
            is String -> description
            is Map<*, *> -> description["value"]?.toString() ?: "Sin descripción"
            else -> "Sin descripción"
        }
}