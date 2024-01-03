package com.example.toaudio.ui.screens.room.views

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toaudio.R
import com.example.toaudio.ui.screens.room.models.Message
import com.example.toaudio.ui.screens.room.models.MessageItem
import com.example.toaudio.ui.theme.ToAudioTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatView(
    modifier: Modifier = Modifier,
    messageList: List<MessageItem>,
    messageValue: String,
    onMessageFieldChanged: (String) -> Unit,
    sendMessageClick: () -> Unit,
){
    Column(
        modifier = modifier,
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.chat),
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.gui_resize_full), contentDescription = "resize_full_chat")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ){
                items(messageList){message ->
                    MessageCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        messageItem = message,
                    )
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.send_message),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                value = messageValue,
                onValueChange =onMessageFieldChanged,
                trailingIcon = {
                    IconButton(onClick = sendMessageClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.send),
                            tint = MaterialTheme.colorScheme.secondary,
                            contentDescription = stringResource(
                                id = R.string.send_message,
                            ),
                        )
                    }
                },
                shape = RoundedCornerShape(size = 10.dp),
            )
        }
    }
}

@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    messageItem: MessageItem
){
    with(messageItem){
        Column(
            modifier = modifier,
            horizontalAlignment = if (isClient){
                Alignment.End
            }
            else{
                Alignment.Start
            }
        ) {
            if(!isClient){
                Text(
                    text = message.username,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Card(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .widthIn(min = 60.dp),
                colors =  CardDefaults.cardColors(
                    containerColor = if (isClient){
                        MaterialTheme.colorScheme.secondary
                    }
                    else{
                        MaterialTheme.colorScheme.background
                    },
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                ),
            ) {
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = message.msg,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(name = "chat_view", device = "id:pixel_3", showSystemUi = true, showBackground = true)
@Composable
fun ChatView_Preview(){
    ToAudioTheme {
        ChatView(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(400.dp)
                .padding(44.dp),
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
            onMessageFieldChanged ={}
        ) {

        }
    }
}