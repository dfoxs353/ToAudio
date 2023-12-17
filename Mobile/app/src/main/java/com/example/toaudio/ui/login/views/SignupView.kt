package com.example.toaudio.ui.login.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toaudio.R
import com.example.toaudio.ui.components.TextInput

@Composable
fun SignUpView(
    modifier: Modifier = Modifier,
    onUserNameFieldChange: (String) -> Unit,
    onPasswordFieldChange: (String) -> Unit,
) {
    var userName by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }



    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.signup_text),
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight(600),
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 72.dp)
        ) {

            Image(
                modifier = Modifier.size(width = 35.dp, height = 30.dp),
                painter = painterResource(id = R.drawable.headphones),
                contentDescription = "",
            )

            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontSize = 21.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight(600),
                )
            )
        }

        TextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            textInputLabel = stringResource(id = R.string.user_name),
            onValueChanged = onUserNameFieldChange,
            value = userName,
        )

        TextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            textInputLabel = stringResource(id = R.string.user_password),
            onValueChanged = onPasswordFieldChange,
            value = password,
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            onClick = { /*TODO*/ },
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(id = R.string.next ),
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight(600),
                )
            )
        }

        TextButton(
            modifier = Modifier.padding(top = 36.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = { /*TODO*/ }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.not_have_account),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = stringResource(id = R.string.signup),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Blue,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Preview(device = "id:pixel_5", showSystemUi = true, showBackground = true)
@Composable
private fun SignUpView_Preview(){
    SignUpView(
        modifier = Modifier.fillMaxSize().padding(horizontal = 44.dp),
        onUserNameFieldChange = {},
        onPasswordFieldChange = {},
    )
}