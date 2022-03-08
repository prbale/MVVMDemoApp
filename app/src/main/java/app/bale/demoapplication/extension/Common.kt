package app.bale.demoapplication.extension

import android.view.View
import android.widget.TextView
import android.R
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun TextView.strikeThrough() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun Context.callANumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
    this.startActivity(intent)
}

fun Context.launchWebsite(webUrl: String) {
    var intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
    val packageManager = this.packageManager
    val list = packageManager?.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    if (list?.isEmpty() == true) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
    }
    startActivity(intent)
}
