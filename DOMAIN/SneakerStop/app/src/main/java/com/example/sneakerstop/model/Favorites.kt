package com.example.sneakerstop.model

import kotlinx.serialization.Serializable

@Serializable
data class Favorites(
    val id: String,
    val product_id: String,
    val user_id: String
)

@Serializable
data class FavoritesInsert(
    val product_id: String,
    val user_id: String
)