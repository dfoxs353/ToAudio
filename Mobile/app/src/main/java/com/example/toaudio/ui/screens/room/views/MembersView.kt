package com.example.toaudio.ui.screens.room.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.toparty.data.remote.websocket.ChatState

@Composable
fun MembersView(
    modifier: Modifier = Modifier,
    membersList: List<String> = listOf(),
    fullScreenClick: () -> Unit = {},
    isMembersFullScreen: Boolean,
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
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            if (isMembersFullScreen){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(10.dp),
                ){
                    items(membersList){member ->
                        MemberView(member = member)
                    }
                }
            }
        }
    }

}


@Composable
fun MemberView(member: String){
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = member, style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Preview
@Composable
fun MemberView_Preview(){
    MemberView(member = "Oleg Platov")
}

@Preview
@Composable
fun MembersView_Preview(){
    MembersView(
        membersList = listOf("Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov","Oleg Platov"),
        isMembersFullScreen = true
    )
}