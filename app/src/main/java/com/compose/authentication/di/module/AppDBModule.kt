package com.compose.authentication.di.module

import android.content.Context
import com.compose.authentication.data.database.AuthenticationDB
import com.compose.authentication.data.database.AuthenticationDao
import com.compose.authentication.di.qualifier.QualifierAuthenticationDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDBModule {

    @QualifierAuthenticationDB
    @Singleton
    @Provides
    fun providesAppDB(@ApplicationContext context: Context): AuthenticationDB = AuthenticationDB.getDB(context)

    @Singleton
    @Provides
    fun provideAuthenticationDao(@QualifierAuthenticationDB db: AuthenticationDB): AuthenticationDao = db.authenticationDao()
}