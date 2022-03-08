package app.bale.demoapplication.data.model

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(type)
        parcel.writeValue(cost)
        parcel.writeValue(original_cost)
        parcel.writeString(provider)
        parcel.writeString(product_link)
        parcel.writeString(image_url)
        parcel.writeValue(like_count)
        parcel.writeValue(comments_count)
        parcel.writeValue(shipping_cost)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Deal> {
        override fun createFromParcel(parcel: Parcel): Deal {
            return Deal(parcel)
        }

        override fun newArray(size: Int): Array<Deal?> {
            return arrayOfNulls(size)
        }
    }
}