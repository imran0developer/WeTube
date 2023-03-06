package com.imran.wetube

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.common.collect.ImmutableList
import com.imran.wetube.constants.Companion.LINK
import com.imran.wetube.constants.Companion.msg

class VideoPlayActivity : AppCompatActivity() {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var progressBar: ProgressBar
    private lateinit var url :Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)


        val link =intent.getStringExtra(LINK)
        msg(link!!,this)
         url = Uri.parse(link)


        setupPlayer()
        //addMP3()
        addMP4Files(url)


        // restore playstate on Rotation
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(restoredMediaItem, seekTime)
                player.play()
            }
        }

    }
    private fun addMP4Files(uri: Uri) {
        val mediaItem = MediaItem.fromUri(uri)
//        val mediaItem2 = MediaItem.fromUri(getString(R.string.myTestMp4))
        val newItems: List<MediaItem> = ImmutableList.of(
            mediaItem,
//            mediaItem2
        )
        player.addMediaItems(newItems)
        player.prepare()
    }
    private fun addMP3() {
        // Build the media item.
        val mediaItem = MediaItem.fromUri(".mp3")
        player.setMediaItem(mediaItem)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.video_view)

        playerView.player = player
//        player.addListener(this)
    }



    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        setupPlayer()
        //addMP3()
        addMP4Files(url)
    }

    // handle loading
    fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE

            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    //get Title from metadata


    // save details if Activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TAG2", "onSaveInstanceState: " + player.currentPosition)
        // current play position
        outState.putLong("SeekTime", player.currentPosition)
        // current mediaItem
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG2", "onSaveInstanceState: " + player.currentPosition)
    }
}