package app.bale.demoapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Deal (
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var type: String? = null,
    var cost: Double? = null,
    var original_cost: Double? = null,
    var provider: String? = null,
    var product_link: String? = null,
    var image_url: String? = null,
    var like_count: Int? = null,
    var comments_count: Int? = null,
    var shipping_cost: Double? = null

) : Parcelable