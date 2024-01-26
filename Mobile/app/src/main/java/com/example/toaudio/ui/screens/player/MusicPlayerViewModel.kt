package com.example.toaudio.ui.screens.player

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import com.example.toaudio.common.EventHandler
import com.example.toaudio.player.service.PlayerEvent
import com.example.toaudio.player.service.ToAudioServiceHandler
import com.example.toaudio.player.service.ToAudioState
import com.example.toaudio.ui.screens.player.models.MusicPlayerEvent
import com.example.toaudio.ui.screens.player.models.MusicPlayerSubState
import com.example.toaudio.ui.screens.player.models.MusicPlayerViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@UnstableApi
@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    private val audioServiceHandler: ToAudioServiceHandler,
) : ViewModel(), EventHandler<MusicPlayerEvent> {

    private val _uiState = MutableLiveData(MusicPlayerViewState())
    val uiState: LiveData<MusicPlayerViewState> = _uiState

    init {
        viewModelScope.launch {
            audioServiceHandler.audioState.collectLatest { mediaState ->
                when (mediaState) {
                    is ToAudioState.Buffering -> calculateProgressValue(mediaState.progress)
                    is ToAudioState.CurrentPlaying -> {
                        Log.d("EXOPLAYER", mediaState.mediaItemIndex.toString())
                    }

                    is ToAudioState.Playing -> _uiState.value?.isPlaying = mediaState.isPlaying
                    is ToAudioState.Progress -> calculateProgressValue(mediaState.progress)
                    is ToAudioState.Ready -> {
                        _uiState.postValue(
                            _uiState.value?.copy(
                                duration = mediaState.duration,
                                playerSubState = MusicPlayerSubState.Ready
                            )
                        )
                    }

                    ToAudioState.Initial -> setInitialPlayerState()
                }
            }
        }
    }


    init {
        setMediaSource()
    }

    private fun setMediaItem() {
        audioServiceHandler.setMediaItemList(
            listOf(
                MediaItem.Builder()
                    .setUri(Uri.parse("https://s3.amazonaws.com/gte619n/test_stream/output_test.m3u8"))
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setAlbumArtist("test")
                            .setDisplayTitle("test")
                            .setSubtitle("test display name")
                            .build()
                    )
                    .build()
            )
        )
    }

    @UnstableApi
    private fun setMediaSource() {
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
        val hlsMediaSource =
            HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri("https://s3.amazonaws.com/gte619n/test_stream/output_test.m3u8"))
        audioServiceHandler.setMediaSource(hlsMediaSource)
    }

    private fun setInitialPlayerState() {
        _uiState.postValue(
            _uiState.value?.copy(
                playerSubState = MusicPlayerSubState.Initial
            )
        )
    }

    private fun formatDuration(duration: Long): String {
        val minute = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
        val seconds = (minute) - minute * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES)
        return String.format("%02d:%02d", minute, seconds)
    }

    private fun calculateProgressValue(currentProgress: Long) {
        _uiState.postValue(
            _uiState.value?.copy(
                progress =
                    if (currentProgress > 0) ((currentProgress.toFloat() / _uiState.value!!.duration.toFloat()) * 100f)
                    else 0f,
                progressString = formatDuration(currentProgress)
            )
        )
    }

    override fun obtainEvent(event: MusicPlayerEvent) {
        viewModelScope.launch {
            when (event) {
                MusicPlayerEvent.Backward -> TODO()
                MusicPlayerEvent.Forward -> TODO()
                MusicPlayerEvent.PlayPause -> {
                    audioServiceHandler.onPlayerEvents(
                        PlayerEvent.PlayPause
                    )
                }

                MusicPlayerEvent.SeekToNext -> TODO()
                is MusicPlayerEvent.UpdateProgress -> TODO()
                is MusicPlayerEvent.SeekTo -> TODO()
            }
        }
    }
}