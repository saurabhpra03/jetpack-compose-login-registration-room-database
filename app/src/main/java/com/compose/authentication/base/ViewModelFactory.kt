package com.compose.authentication.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.authentication.data.network.CoroutineDispatcherProvider
import com.compose.authentication.viewmodel.AuthViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    @ApplicationContext private val context: Context,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val mainRepository: MainRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                return AuthViewModel(context, coroutineDispatcherProvider, mainRepository) as T
            }

            else -> {
                super.create(modelClass)
            }
        }
    }
}