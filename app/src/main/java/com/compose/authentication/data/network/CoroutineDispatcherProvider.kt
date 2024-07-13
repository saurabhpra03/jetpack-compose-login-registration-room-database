package com.compose.authentication.data.network

import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider {

    fun IO() = Dispatchers.IO
    fun Main() = Dispatchers.Main
    fun Default() = Dispatchers.Default

}