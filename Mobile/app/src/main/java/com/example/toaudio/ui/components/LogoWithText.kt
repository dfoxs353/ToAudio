package com.example.toaudio.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.toaudio.R

@Composable
fun LogoWithText(
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            modifier = Modifier.size(width = 35.dp, height = 30.dp),
            painter = painterResource(id = R.drawable.headphones),
            contentDescription = "",
        )

        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleSmall,
        )
    }
}