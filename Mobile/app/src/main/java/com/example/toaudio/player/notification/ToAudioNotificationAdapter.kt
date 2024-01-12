package com.example.toaudio.player.notification

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerNotificationManager
import coil.Coil
import coil.request.ImageRequest

@UnstableApi
class ToAudioNotificationAdapter(
    private val context: Context,
    private val pendingIntent: PendingIntent?,
) : PlayerNotificationManager.MediaDescriptionAdapter {
    override fun getCurrentContentTitle(player: Player): CharSequence =
        player.mediaMetadata.albumTitle ?: "Unknown"

    override fun createCurrentContentIntent(player: Player): PendingIntent? = pendingIntent

    override fun getCurrentContentText(player: Player): CharSequence =
        player.mediaMetadata.displayTitle ?: "Unknown"

    override fun getCurrentLargeIcon(
        player: Player,
        callback: PlayerNotificationManager.BitmapCallback,
    ): Bitmap? {

        val artworkUri = player.mediaMetadata.artworkUri
        if (artworkUri != null) {
            val request = ImageRequest.Builder(context)
                .data(artworkUri)
                .target {
                    callback.onBitmap(it.toBitmap())
                }
                .build()
            Coil.imageLoader(context).enqueue(request)
        }

        return null
    }
}