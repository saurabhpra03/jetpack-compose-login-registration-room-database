package com.compose.authentication.base

import com.compose.authentication.data.database.AuthenticationDao
import com.compose.authentication.data.model.Authentication
import javax.inject.Inject

class MainRepository @Inject constructor() {

    @Inject lateinit var authenticationDao: AuthenticationDao

    suspend fun checkEmailIdExists(emailId: String): Int = authenticationDao.checkEmailIdExists(emailId)

    suspend fun createAccount(authentication: Authentication) = authenticationDao.createAccount(authentication)

    suspend fun signIn(emailId: String, password: String): Authentication? = authenticationDao.signIn(emailId, password)

}