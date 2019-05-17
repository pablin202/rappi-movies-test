package com.rappi.samplemovies.presenter.detailmovie

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.common.ItemOffsetDecoration
import com.rappi.samplemovies.presenter.common.RxBus
import com.rappi.samplemovies.presenter.common.VideoItemListener
import com.rappi.samplemovies.domain.models.VideoModel
import com.rappi.samplemovies.presenter.trailervideomovie.PlayYoutubeActivity
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import java.util.*


class MovieDetailFragment : Fragment() {

    private var posterUrl: String? = null
    private var title: String? = null
    private var overview: String? = null
    private var releaseDate: String? = null
    private var voteAverage: Double? = null

    private lateinit var _view: View
    private var videosAdapter: MovieVideosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            posterUrl = it.getString(DetailActivity.MOVIE_POSTER_URL)
            title = it.getString(DetailActivity.MOVIE_TITLE)
            overview = it.getString(DetailActivity.MOVIE_OVERVIEW)
            releaseDate = it.getString(DetailActivity.MOVIE_RELEASE_DATE)
            voteAverage = it.getDouble(DetailActivity.MOVIE_VOTE_AVERAGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        subscribeEvent()
        return _view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        posterUrl?.let {
            Picasso.get()
                .load(it)
                .into(image_movie_detail_poster)
        }

        text_movie_original_title.text = title
        text_movie_user_rating.text = String.format(Locale.US, "%.1f", voteAverage)

        val releaseDateFormatted = String.format(
            getString(R.string.movie_detail_release_date),
            releaseDate
        )

        text_movie_release_date.text = releaseDateFormatted
        text_movie_overview.text = overview

        card_movie_videos_content.visibility = View.GONE

        initVideosList()
    }

    @SuppressWarnings("CheckResult")
    private fun subscribeEvent() {

        RxBus.listen(MovieDetailsViewState::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                card_movie_videos_content.visibility = View.VISIBLE
                videosAdapter!!.setMovieVideos(it.videos)

            }, { throwable ->
                Log.e("RXBus", throwable.message)
            })

    }

    private fun initVideosList() {

        videosAdapter =  (MovieVideosAdapter(object : VideoItemListener {

            override fun onClick(video: VideoModel, position: Int) {
                var intent = Intent(context!!, PlayYoutubeActivity::class.java)
                intent.putExtra(PlayYoutubeActivity.YOUTUBE_KEY, video.youtubeKey)
                startActivity(intent)
            }

        }))

        movie_videos.adapter = videosAdapter
        movie_videos.itemAnimator = DefaultItemAnimator()
        movie_videos.addItemDecoration(ItemOffsetDecoration(context!!, R.dimen.movie_item_offset))
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        movie_videos.layoutManager = layoutManager
    }

    companion object {

        @JvmStatic
        fun newInstance(posterUrl: String, title:String, overview: String, voteAverage: Double, releaseDate: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putCharSequence(DetailActivity.MOVIE_POSTER_URL, posterUrl)
                    putCharSequence(DetailActivity.MOVIE_TITLE, title)
                    putCharSequence(DetailActivity.MOVIE_OVERVIEW, overview)
                    putCharSequence(DetailActivity.MOVIE_RELEASE_DATE, releaseDate)
                    putDouble(DetailActivity.MOVIE_VOTE_AVERAGE, voteAverage)
                }
            }
    }
}