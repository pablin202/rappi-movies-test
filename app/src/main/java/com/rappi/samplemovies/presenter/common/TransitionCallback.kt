package com.rappi.samplemovies.presenter.common

import android.transition.Transition

class TransitionCallback (private var callback: () -> Unit): Transition.TransitionListener {

    override fun onTransitionEnd(transition: Transition?) {
        callback()
    }

    override fun onTransitionResume(transition: Transition?) {

    }

    override fun onTransitionPause(transition: Transition?) {

    }

    override fun onTransitionCancel(transition: Transition?) {

    }

    override fun onTransitionStart(transition: Transition?) {

    }

}