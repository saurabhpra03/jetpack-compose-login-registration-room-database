package com.compose.authentication.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Next,
    isPassword: Boolean = false,
    errorMsg: String
) {

    var showPassword by remember { mutableStateOf(false) }


    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                autoCorrect = false,
                imeAction = imeAction
            ),
            trailingIcon = {
                if (value.isNotEmpty() && isPassword)
                    Icon(
                        imageVector = if (!showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                showPassword = !showPassword
                            }
                    )
            },
            visualTransformation = when {
                isPassword && !showPassword -> PasswordVisualTransformation()
                else -> VisualTransformation.None
            },
        )

        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = errorMsg,
            style = TextStyle(
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                color = Color.Red

            )
        )

        Spacer(modifier = Modifier.height(10.dp))
    }

}