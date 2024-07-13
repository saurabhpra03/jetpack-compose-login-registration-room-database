package com.compose.authentication.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBtn(
    btnName: String,
    onClick: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = onClick) {
        Text(text = btnName,
            style = TextStyle(
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                color = Color.White
            )
        )
    }

}