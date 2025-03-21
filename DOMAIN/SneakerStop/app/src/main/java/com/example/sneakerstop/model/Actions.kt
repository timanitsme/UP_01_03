package com.example.sneakerstop.model

import kotlinx.serialization.Serializable

@Serializable
data class Actions(
    val id: String,
    val created_at: String,
    val photo: String
)