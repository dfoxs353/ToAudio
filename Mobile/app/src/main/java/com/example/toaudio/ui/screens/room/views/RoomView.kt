package com.example.toaudio.ui.screens.room.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toaudio.R
import com.example.toaudio.ui.components.CopyText
import com.example.toaudio.ui.components.LogoWithText
import com.example.toaudio.data.models.TextMessage
import com.example.toaudio.domain.models.MessageItem
import com.example.toaudio.ui.theme.ToAudioTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoomView(
    modifier: Modifier = Modifier,
    messageList: List<MessageItem>,
    membersList: List<String> = listOf(),
    messageValue: String,
    onMessageValueChanged: (String) -> Unit,
    sendMessageClick: () -> Unit,
    roomId: String,
) {
    val context = LocalContext.current

    var isChatFullScreen by remember { mutableStateOf(false) }
    var isMembersFullScreen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.people),
                    contentDescription = "my_account",
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
            LogoWithText()
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.sound_on),
                    contentDescription = "sound_off",
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }


        AnimatedVisibility(
            visible = !isChatFullScreen && !isMembersFullScreen,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    text = stringResource(id = R.string.link),
                    style = MaterialTheme.typography.titleSmall,
                )

                CopyText(context = context, textValue = roomId, labelValue = "Room Id")
            }
        }

        AnimatedVisibility(
            visible = !isChatFullScreen,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
        ) {

            MembersView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                fullScreenClick = {
                    isMembersFullScreen = !isMembersFullScreen
                },
                isMembersFullScreen = isMembersFullScreen,
                membersList = membersList,
            )

        }

        AnimatedVisibility(
            modifier = Modifier.padding(bottom = 56.dp),
            visible = !isMembersFullScreen,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
        ) {
            ChatView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                messageList = messageList,
                messageValue = messageValue,
                onMessageFieldChanged = onMessageValueChanged,
                sendMessageClick = sendMessageClick,
                fullScreenClick = {
                    isChatFullScreen = !isChatFullScreen
                },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoomView_Preview() {
    ToAudioTheme {
        RoomView(
            messageList = listOf(
                MessageItem(TextMessage("test", "hi"), false),
                MessageItem(TextMessage("test", "hi"), true),
                MessageItem(TextMessage("test", "hi"), false),
                MessageItem(TextMessage("test", "hi"), true),
                MessageItem(TextMessage("test", "hi"), true),
                MessageItem(TextMessage("test", "hi"), false),
                MessageItem(TextMessage("test", "hi"), true),
                MessageItem(TextMessage("test", "hi"), true),
                MessageItem(TextMessage("test", "hi"), true),
                MessageItem(TextMessage("test", "hi"), false),
                MessageItem(TextMessage("test", "hi"), true),
            ),
            messageValue = "",
            onMessageValueChanged = {},
            sendMessageClick = {},
            roomId = "sasafsaf",
        )
    }
}


