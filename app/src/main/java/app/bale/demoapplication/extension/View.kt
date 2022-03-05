package app.bale.demoapplication.extension

import android.view.View
import android.widget.TextView
import android.R
import android.graphics.Paint


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun TextView.strickthrough() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}