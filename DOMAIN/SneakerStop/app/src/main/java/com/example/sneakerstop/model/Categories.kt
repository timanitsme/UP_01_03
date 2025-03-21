package com.example.sneakerstop.model

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val id: String,
    val title: String,
)