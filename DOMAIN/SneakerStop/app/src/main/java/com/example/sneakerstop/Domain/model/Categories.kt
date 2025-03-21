package com.example.sneakerstop.Domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val id: String,
    val title: String,
)