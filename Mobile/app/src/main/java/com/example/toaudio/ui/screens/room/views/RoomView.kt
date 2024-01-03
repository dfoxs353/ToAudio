package com.example.toaudio.ui.screens.room.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toaudio.R
import com.example.toaudio.ui.components.LogoWithText
import com.example.toaudio.ui.screens.room.models.Message
import com.example.toaudio.ui.screens.room.models.MessageItem
import com.example.toaudio.ui.theme.ToAudioTheme

@Composable
fun RoomView(
    modifier: Modifier =  Modifier,
    messageList: List<MessageItem>,
    messageValue: String,
    onMessageValueChanged: (String) -> Unit,
    sendMessageClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.people), contentDescription = "my_account")
            }
            LogoWithText()
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.sound_on), contentDescription = "sound_off")
            }
        }
        ChatView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp),
            messageList = messageList,
            messageValue = messageValue,
            onMessageFieldChanged = onMessageValueChanged,
            sendMessageClick = sendMessageClick,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoomView_Preview(){
    ToAudioTheme {
        RoomView(
            messageList = listOf(
                MessageItem(Message("test","hi"),false),
                MessageItem(Message("test","hi"),true),
                MessageItem(Message("test","hi"),false),
                MessageItem(Message("test","hi"),true),
                MessageItem(Message("test","hi"),true),
                MessageItem(Message("test","hi"),false),
                MessageItem(Message("test","hi"),true),
                MessageItem(Message("test","hi"),true),
                MessageItem(Message("test","hi"),true),
                MessageItem(Message("test","hi"),false),
                MessageItem(Message("test","hi"),true),
            ),
            messageValue = "",
            onMessageValueChanged ={},
        ) {

        }
    }
}


