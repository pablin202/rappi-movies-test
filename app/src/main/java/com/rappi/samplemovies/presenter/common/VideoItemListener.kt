package com.rappi.samplemovies.presenter.common

import com.rappi.samplemovies.domain.models.VideoModel

interface VideoItemListener {
    fun onClick(video: VideoModel, position: Int)
}