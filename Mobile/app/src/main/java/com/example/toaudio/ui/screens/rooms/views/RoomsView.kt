package com.example.toaudio.ui.screens.rooms.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toaudio.R
import com.example.toaudio.ui.components.TextInput

@Composable
fun RoomsView(
    modifier: Modifier = Modifier,
    roomIdValue: String,
    onRoomIdFieldChanged: (String) -> Unit,
    enterRoomClick: () -> Unit,
    createRoomClick: () -> Unit,
){
    Column(
        modifier = modifier,
    ) {
        TextInput(
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            textInputLabel = stringResource(id = R.string.lobby_id),
            onValueChanged = onRoomIdFieldChanged,
            value = roomIdValue
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            onClick =   enterRoomClick,
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(id = R.string.enter_lobby ),
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight(600),
                )
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            onClick =   createRoomClick,
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(id = R.string.create_lobby ),
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight(600),
                )
            )
        }
    }
}

@Preview(device = "id:pixel_5", showSystemUi = true, showBackground = true)
@Composable
private fun RoomsView_Preview(){
    RoomsView(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        roomIdValue = "",
        onRoomIdFieldChanged = {},
        enterRoomClick = {},
        createRoomClick = {},
    )
}
