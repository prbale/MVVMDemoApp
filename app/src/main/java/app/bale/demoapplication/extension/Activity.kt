package app.bale.demoapplication.extension

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity?.sendAnEmail(email: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(email))
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, body)
    intent.data = Uri.parse("mailto:")

    if (this?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
        startActivity(intent)
    } else {
        Toast.makeText(
            this, "There is no application that support this action",
            Toast.LENGTH_SHORT
        ).show()
    }
}

