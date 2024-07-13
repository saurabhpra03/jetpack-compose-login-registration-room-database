package com.compose.authentication.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.authentication.R
import com.compose.authentication.common.AppBtn
import com.compose.authentication.common.AppField
import com.compose.authentication.data.SharedPref
import com.compose.authentication.utils.Constants.finishAndGoToNextScreen
import com.compose.authentication.utils.Constants.logD
import com.compose.authentication.utils.Screen
import com.compose.authentication.utils.Validator
import com.compose.authentication.utils.showLongToast
import com.compose.authentication.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    sharedPref: SharedPref) {

    val tag = "Login"

    val context = LocalContext.current

    var emailId by remember { mutableStateOf("") }
    var isErrorEmailId by remember { mutableStateOf(false) }
    var errorEmailId by remember { mutableStateOf("") }
    val updateEmailId = { data: String ->
        emailId = data.trim()
        errorEmailId = Validator.validateEmailId(context, emailId)
        isErrorEmailId = errorEmailId.isNotEmpty()
    }

    var password by remember { mutableStateOf("") }
    var isErrorPassword by remember { mutableStateOf(false) }
    var errorPassword by remember { mutableStateOf("") }
    val updatePassword = { data: String ->
        password = data.trim()
        errorPassword = Validator.validatePassword(context, password)
        isErrorPassword = errorPassword.isNotEmpty()
    }

    fun validation(): Boolean {
        updateEmailId(emailId)
        updatePassword(password)

        return !(isErrorEmailId || isErrorPassword)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppField(
            value = emailId,
            onValueChange = updateEmailId,
            isError = isErrorEmailId,
            label = stringResource(id = R.string.email_id),
            keyboardType = KeyboardType.Email,
            errorMsg = errorEmailId
        )

        AppField(
            value = password,
            onValueChange = updatePassword,
            isError = isErrorPassword,
            label = stringResource(id = R.string.password),
            keyboardType = KeyboardType.Text,
            errorMsg = errorPassword,
            isPassword = true,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(40.dp))

        AppBtn(btnName = stringResource(id = R.string.sign_in)) {
            if (validation()) {
                viewModel.signIn(emailId, password)
            }
        }

        Text(
            modifier = Modifier
                .padding(top = 11.dp)
                .clickable {
                    navController.finishAndGoToNextScreen("sign_up")
                },
            text = stringResource(id = R.string.not_account_sign_up),
            style = TextStyle(
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        )

        viewModel.authFlow.collectAsState().value?.let {
            when(it){
                is AuthViewModel.AuthUIState.Success -> {
                    tag.logD("success: ${it.data}")
                    sharedPref.saveAuhDetails(it.data)
                    navController.finishAndGoToNextScreen(Screen.Dashboard.route)
                }
                is AuthViewModel.AuthUIState.Error -> {
                    context.showLongToast(it.message)
                    viewModel.authFlow.value = AuthViewModel.AuthUIState.Clear
                }
                is AuthViewModel.AuthUIState.Loading-> {
                    CircularProgressIndicator()
                }
                else -> {
                    tag.logD("authFlow clear")
                }
            }
        }
    }
}