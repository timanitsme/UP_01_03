package com.example.sneakerstop.Domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Products(
    val id: String,
    val title: String,
    val category_id: String,
    val cost: Float,
    val description: String,
    val is_best_seller: Boolean,
    val image: String
)