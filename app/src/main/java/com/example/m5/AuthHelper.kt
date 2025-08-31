package com.example.m5

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.UUID

object AuthHelper {
    private const val FILE_NAME = "users.json"
    private val gson = Gson()
    private val userList = mutableListOf<User>()

    private fun loadUsers(context: Context) {
        try {
            val file = File(context.filesDir, FILE_NAME)
            if (file.exists()) {
                val json = file.readText()
                val type = object : TypeToken<MutableList<User>>() {}.type
                userList.clear()
                userList.addAll(gson.fromJson(json, type))
            }
        } catch (e: Exception) {
            Log.e("AuthHelper", "Error loading users", e)
        }
    }

    private fun saveUsers(context: Context) {
        try {
            val json = gson.toJson(userList)
            File(context.filesDir, FILE_NAME).writeText(json)
        } catch (e: Exception) {
            Log.e("AuthHelper", "Error saving users", e)
        }
    }

    fun registerUser(context: Context, user: User): Boolean {
        loadUsers(context) // Ensure we have latest data
        if (userList.any { it.email == user.email }) return false
        userList.add(user)
        saveUsers(context)
        return true
    }

    fun login(context: Context, email: String, password: String): User? {
        loadUsers(context) // Ensure we have latest data
        return userList.find { it.email == email && it.password == password }
    }

    fun generateResetToken(context: Context, email: String): String? {
        loadUsers(context) // Ensure we have latest data
        val user = userList.find { it.email == email } ?: return null
        val token = UUID.randomUUID().toString()
        user.resetToken = token
        user.tokenExpiry = System.currentTimeMillis() + 3600000 // 1 hour expiry
        saveUsers(context)
        return token
    }

    fun resetPassword(context: Context, token: String, newPassword: String): Boolean {
        loadUsers(context) // Ensure we have latest data
        val user = userList.find { it.resetToken == token } ?: return false
        if (user.tokenExpiry ?: 0 < System.currentTimeMillis()) return false

        user.password = newPassword
        user.resetToken = null
        user.tokenExpiry = null
        saveUsers(context)
        return true
    }
}