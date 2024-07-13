package com.compose.authentication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.compose.authentication.data.model.Authentication

@Dao
interface AuthenticationDao {

    @Query("SELECT COUNT() FROM authentication WHERE emailId = :emailId")
    suspend fun checkEmailIdExists(emailId: String) : Int

    @Insert
    suspend fun createAccount(authentication: Authentication)

    @Query("SELECT * FROM authentication WHERE emailId = :emailId AND password = :password")
    suspend fun signIn(emailId: String, password: String) : Authentication?
}