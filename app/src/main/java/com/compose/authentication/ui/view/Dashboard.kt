package com.compose.authentication.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.compose.authentication.R
import com.compose.authentication.data.SharedPref
import com.compose.authentication.utils.Constants.finishAndGoToNextScreen
import com.compose.authentication.utils.Screen

@Composable
fun DashboardScreen(
    navController: NavController,
    sharedPref: SharedPref,
) {

    val authentication = sharedPref.getAuthDetails()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        val (refUserName, refLogout) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(refUserName) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            text = "Hi, ${authentication?.name ?: ""}",
            style = TextStyle(
                fontSize = 17.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                color = Color.Black
            )
        )

        Button(
            modifier = Modifier
                .constrainAs(refLogout) {
                    top.linkTo(refUserName.bottom)
                }
                .padding(top = 30.dp)
                .fillMaxWidth()
                .height(55.dp),
            onClick = {
                sharedPref.logout()
                navController.finishAndGoToNextScreen(Screen.Login.route)
            }) {
            Text(text = stringResource(id = R.string.logout),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W500,
                    color = Color.White
                )
            )
        }

    }

}