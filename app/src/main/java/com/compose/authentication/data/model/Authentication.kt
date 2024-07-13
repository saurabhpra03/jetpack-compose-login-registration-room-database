package com.compose.authentication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Authentication(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val emailId: String,
    val password: String
)
