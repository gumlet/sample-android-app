package com.gumlet.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gumlet.video.player.GumletInitParams
import com.gumlet.video.player.GumletPlayerListener
import com.gumlet.video.player.GumletPlayerView

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var playerView: GumletPlayerView
    private lateinit var statusTextView: TextView
    private lateinit var btnPlayHls: Button
    private lateinit var btnPlayDash: Button
    private lateinit var btnPlayDrm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        initializeViews()
        setupPlayer()
        setupButtons()
    }

    private fun initializeViews() {
        playerView = findViewById(R.id.gumlet_player_view)
        statusTextView = findViewById(R.id.tv_status)
        btnPlayHls = findViewById(R.id.btn_play_hls)
        btnPlayDash = findViewById(R.id.btn_play_dash)
        btnPlayDrm = findViewById(R.id.btn_play_drm)
    }

    private fun setupPlayer() {
        // Set up player listener to handle events and errors
        playerView.setPlayerListener(object : GumletPlayerListener {
            override fun onPlayerError(error: String) {
                Log.e("GumletPlayer", "Playback Error: $error")
                statusTextView.text = "Error: $error"
                Toast.makeText(this@VideoPlayerActivity, "Playback Error: $error", Toast.LENGTH_LONG).show()
            }

            override fun onPlayerStateChanged(isPlaying: Boolean) {
                val status = if (isPlaying) "Playing" else "Paused"
                statusTextView.text = "Status: $status"
                Log.d("GumletPlayer", "Player State Changed: $status")
            }
        })
    }

    private fun setupButtons() {
        // Example 1: Play HLS Video (.m3u8)
        btnPlayHls.setOnClickListener {
            playHlsVideo()
        }

        // Example 2: Play DASH Video (.mpd)
        btnPlayDash.setOnClickListener {
            playDashVideo()
        }

        // Example 3: Play DRM Protected Video
        btnPlayDrm.setOnClickListener {
            playDrmVideo()
        }
    }

    /**
     * Example 1: Simple HLS Video Playback
     * Replace the URL with your actual HLS video URL
     */
    private fun playHlsVideo() {
        statusTextView.text = "Loading HLS video..."
        
        val params = GumletInitParams.Builder()
            .setVideoUrl("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8") // Sample HLS URL
            .setAutoPlay(true)
            .build()

        playerView.load(params)
        Log.d("GumletPlayer", "Loading HLS video")
    }

    /**
     * Example 2: DASH Video Playback
     * Replace the URL with your actual DASH video URL
     */
    private fun playDashVideo() {
        statusTextView.text = "Loading DASH video..."
        
        val params = GumletInitParams.Builder()
            .setVideoUrl("https://dash.akamaized.net/akamai/bbb_30fps/bbb_30fps.mpd") // Sample DASH URL
            .setAutoPlay(true)
            .build()

        playerView.load(params)
        Log.d("GumletPlayer", "Loading DASH video")
    }

    /**
     * Example 3: DRM Protected Video Playback
     * Replace the URLs with your actual DRM video URL and license server URL
     */
    private fun playDrmVideo() {
        statusTextView.text = "Loading DRM protected video..."
        
        // Note: Replace these with your actual DRM video URL and license server URL
        val params = GumletInitParams.Builder()
            .setVideoUrl("https://example.com/protected-video.mpd") // Your DRM protected video URL
            .setDrmLicenseUrl("https://license-server.com/widevine") // Your Widevine license server URL
            .setAutoPlay(true)
            .build()

        playerView.load(params)
        Log.d("GumletPlayer", "Loading DRM protected video")
        
        Toast.makeText(
            this,
            "Note: Replace URLs with your actual DRM video and license server URLs",
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * Lifecycle Management
     * CRUCIAL: Forward lifecycle events to prevent memory leaks
     */
    override fun onPause() {
        super.onPause()
        playerView.onPause()
    }

    override fun onResume() {
        super.onResume()
        playerView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        playerView.onDestroy()
    }
}
