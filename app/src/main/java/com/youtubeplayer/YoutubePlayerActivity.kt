package com.youtubeplayer

// https://www.youtube.com/watch?v=qPNiIeKMHyg
// https://www.youtube.com/watch?v=FYxUJFD9Ye4&list=RDCLAK5uy_kLWIr9gv1XLlPbaDS965-Db4TrBoUTxQ8&start_radio=1&rv=WWCsGEarExg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID_KEY = "qPNiIeKMHyg"
const val PLAYLIST_ID_KEY = "RDCLAK5uy_kLWIr9gv1XLlPbaDS965-Db4TrBoUTxQ8&start_radio=1&rv=WWCsGEarExg"


class YoutubePlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{
    val TAG = "YoutubePlayerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube_layer)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube_player, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 100)
//        button1.text = "Button added"
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize("AIzaSyDSUvlV3uD3yzbYcjvKVRzcPM3G2mNScd8", this)

    }

    override fun onInitializationSuccess( pl: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean ){
        Log.d(TAG, "onInitializationSuccess")
        Toast.makeText(this, "Initialized successfully", Toast.LENGTH_LONG).show()

        player?.setPlaybackEventListener(playbackEventListener)
        player?.setPlayerStateChangeListener(changeStateListener)

        if(!wasRestored){
            player?.cueVideo(YOUTUBE_VIDEO_ID_KEY)
        }
    }


    override fun onInitializationFailure( provider: YouTubePlayer.Provider?, YouTubeInitializationResult: YouTubeInitializationResult? ) {
        Log.d(TAG, "onInitializationFailure")
        val REQUEST_CODE = 0
        if(YouTubeInitializationResult?.isUserRecoverableError == true){
            YouTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            Toast.makeText(this, "Error starting player", Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@YoutubePlayerActivity, "Playing", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubePlayerActivity, "Paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }

    }

    private val changeStateListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubePlayerActivity, "Add", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubePlayerActivity, "End", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }

    }

}