package com.rappi.samplemovies.presenter.trailervideomovie


import android.os.Bundle
import com.rappi.samplemovies.R
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.rappi.samplemovies.presenter.common.Constants
import com.rappi.samplemovies.presenter.common.toast
import kotlinx.android.synthetic.main.activity_play_youtube.*

class PlayYoutubeActivity : YouTubeBaseActivity () {

    companion object {
        const val YOUTUBE_KEY: String = "youtube_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_youtube)

        val url = intent.getStringExtra(YOUTUBE_KEY)

        player.initialize(Constants.YOUTUBE_KEY, object : YouTubePlayer.OnInitializedListener{

            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, p2: Boolean) {
                youTubePlayer?.let {
                    it.loadVideo(url)
                }
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                toast("Youtube Failed!")
            }

        })
    }
}
