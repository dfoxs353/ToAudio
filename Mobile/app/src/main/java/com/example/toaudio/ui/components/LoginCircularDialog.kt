package com.example.toaudio.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginCircularDialog(titleText: String){
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ },
        title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = titleText,
                    textAlign = TextAlign.Center,
                )
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(60.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },
    )
}

@Preview
@Composable
fun LoginCircularDialog_Preview(){
    LoginCircularDialog(
        titleText = "TitleText"
    )
}