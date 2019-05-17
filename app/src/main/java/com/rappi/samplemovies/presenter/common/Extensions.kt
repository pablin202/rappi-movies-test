package com.rappi.samplemovies.presenter.common

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rappi.samplemovies.presenter.App

val Activity.app: App
    get() = application as App

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!


fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, resourceId, duration).show()

fun Activity.snackBar(
    message: CharSequence,
    view: View?,
    duration: Int = Snackbar.LENGTH_SHORT,
    action: String? = null,
    actionEv: (v: View) -> Unit = {}
) {

    if (view != null) {
        val snackBar = Snackbar.make(view, message, duration)

        if (!action.isNullOrEmpty()) {
            snackBar.setAction(action, actionEv)
        }

        snackBar.show()
    }
}