package com.example.toaudio.ui.screens.room.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toaudio.R

@Composable
fun MembersView(
    modifier: Modifier = Modifier,
    fullScreenClick: () -> Unit = {},
){

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.members),
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(
                onClick = fullScreenClick
            ) {
                Icon(painter = painterResource(id = R.drawable.gui_resize_full), contentDescription = "resize_full_chat")
            }
        }

        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 10.dp))
                .fillMaxWidth()
                .height(51.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {

        }
    }

}

@Preview
@Composable
fun MemberView_Preview(){
    MembersView(
    )
}