package com.youtubeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeStandalonePlayer
import java.lang.IllegalArgumentException

class Menu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSingle = findViewById<Button>(R.id.btn_single)
        val btnPlaylist = findViewById<Button>(R.id.btn_Menu)

        btnSingle.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when(view.id){
            R.id.btn_single -> YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyDSUvlV3uD3yzbYcjvKVRzcPM3G2mNScd8", YOUTUBE_VIDEO_ID_KEY)
            R.id.btn_Menu -> YouTubeStandalonePlayer.createPlaylistIntent(this, "AIzaSyDSUvlV3uD3yzbYcjvKVRzcPM3G2mNScd8", PLAYLIST_ID_KEY)
            else -> throw IllegalArgumentException("Invalid button")
        }
        startActivity(intent)
    }
}