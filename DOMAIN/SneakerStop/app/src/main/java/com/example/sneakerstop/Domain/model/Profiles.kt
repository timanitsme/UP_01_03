package com.example.sneakerstop.Domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Profiles(
    val id: String,
    val created_at: String?,
    val user_id: String,
    var photo: String?,
    var firstname: String?,
    var lastname: String?,
    var address: String?,
    var phone: String?
)

@Serializable
data class ProfileCreate(
    val user_id: String,
    val firstname: String,
)

@Serializable
data class ProfileUpdate(
    val firstname: String,
    val lastname: String?,
    val address: String?,
    val phone: String?
)