package com.example.sneakerstop.model

import kotlinx.serialization.Serializable

@Serializable
data class Profiles(
    val id: String,
    val created_at: String?,
    val user_id: String,
    val photo: String,
    val firstname: String,
    val lastname: String,
    val address: String,
    val phone: String
)

@Serializable
data class ProfileCreate(
    val user_id: String,
    val firstname: String,
)