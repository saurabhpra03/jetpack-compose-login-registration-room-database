package com.compose.authentication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.authentication.R
import com.compose.authentication.base.MainRepository
import com.compose.authentication.data.model.Authentication
import com.compose.authentication.data.network.CoroutineDispatcherProvider
import com.compose.authentication.utils.logE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext private  val context: Context,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val mainRepository: MainRepository,) :
    ViewModel() {

    private val tag = AuthViewModel::class.java.simpleName

    private val _authFlow = MutableStateFlow<AuthUIState?>(null)
    val authFlow: MutableStateFlow<AuthUIState?> get() = _authFlow

    fun checkEmailIdExists(authentication: Authentication){
        _authFlow.value = AuthUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.checkEmailIdExists(authentication.emailId)

                if (response > 0){
                    _authFlow.value = AuthUIState.Error(context.getString(R.string.email_already_exists))
                }else{
                    createAccount(authentication)
                }
            }catch (e: Exception){
                tag.logE("checkEmailIdExists, error: ${e.message}")
                _authFlow.value = AuthUIState.Error(context.getString(R.string.something_went_wrong))
            }
        }
    }

    private fun createAccount(authentication: Authentication) {
        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                mainRepository.createAccount(authentication)
                signIn(authentication.emailId, authentication.password)
            } catch (ex: Exception) {
                tag.logE("createAccount, error: ${ex.message}")
                _authFlow.value = AuthUIState.Error(context.getString(R.string.something_went_wrong))
            }
        }
    }

     fun signIn(emailId: String, password: String){
        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                val response = mainRepository.signIn(emailId, password)
                response?.let {
                    _authFlow.value = AuthUIState.Success(it)
                } ?: run {
                    _authFlow.value = AuthUIState.Error(context.getString(R.string.email_or_password_not_exists))
                }
            }catch (ex: Exception){
                tag.logE("createAccount, error: ${ex.message}")
                _authFlow.value = AuthUIState.Error(context.getString(R.string.something_went_wrong))
            }
        }
    }

    sealed class AuthUIState{
        data object Clear: AuthUIState()
        data object Loading: AuthUIState()
        class Success(val data: Authentication): AuthUIState()
        class Error(val message: String): AuthUIState()
    }
}