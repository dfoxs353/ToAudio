package com.example.toaudio.ui.screens.player.views

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffoldDefaults
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.toaudio.R
import com.example.toaudio.player.service.ToAudioService
import com.example.toaudio.ui.screens.player.models.MusicPlayerSubState
import com.example.toaudio.ui.screens.player.models.MusicPlayerViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MusicPlayerView(
    sheetState: BottomSheetState,
    scaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    title: String,
    author: String,
    isPlaying: Boolean,
    coverUri: Uri,
    onForwardClick: () -> Unit,
    onBackwardClick: () -> Unit,
    onPlayOrPauseClick: () -> Unit,
) {

    AnimatedVisibility(
        modifier = Modifier
            .clickable {
                scope.launch {
                    sheetState.expand()
                }
            },
        visible = scaffoldState.bottomSheetState.isCollapsed,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally(),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .height(BottomSheetScaffoldDefaults.SheetPeekHeight),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier
                        .alpha(0.5f),
                    text = author,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            MusicIconButton(
                modifier = Modifier.size(20.dp),
                painter = if (isPlaying) {
                    painterResource(
                        id = R.drawable.pause
                    )
                } else {
                    painterResource(id = R.drawable.play)
                },
                onClick = onPlayOrPauseClick
            )
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                model = coverUri,
                contentDescription = "",
                error = painterResource(id = R.drawable.headphones)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                modifier = Modifier
                    .alpha(0.5f),
                text = author,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.size(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                MusicIconButton(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.backward),
                    onClick = onBackwardClick
                )
                Spacer(modifier = Modifier.size(10.dp))
                MusicIconButton(
                    modifier = Modifier.size(40.dp),
                    painter = if (isPlaying) {
                        painterResource(
                            id = R.drawable.pause
                        )
                    } else {
                        painterResource(id = R.drawable.play)
                    },
                    onClick = onPlayOrPauseClick
                )
                Spacer(modifier = Modifier.size(10.dp))
                MusicIconButton(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.foward),
                    onClick = onForwardClick
                )
            }
        }
    }
}

@Composable
fun MusicIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.surfaceTint,
        )
    }
}