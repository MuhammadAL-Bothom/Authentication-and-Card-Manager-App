package com.example.m5
data class User(
    val email: String,
    var password: String, // In real app, store only hashed passwords
    val name: String,
    val phone: String,
    var resetToken: String? = null,
    var tokenExpiry: Long? = null
)