package com.compose.authentication.di.module

import com.compose.authentication.data.network.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesCoroutineDispatcher() = CoroutineDispatcherProvider()
}