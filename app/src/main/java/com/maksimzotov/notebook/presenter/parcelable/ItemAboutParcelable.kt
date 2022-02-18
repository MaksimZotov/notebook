package com.maksimzotov.notebook.presenter.parcelable

import android.os.Parcel
import android.os.Parcelable
import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.presenter.main.util.Constants.EMPTY_STRING

data class ItemAboutParcelable(
    val title: String,
    val urlToImage: String,
    val urlToWebPage: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: EMPTY_STRING,
        parcel.readString() ?: EMPTY_STRING,
        parcel.readString() ?: EMPTY_STRING
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(urlToImage)
        parcel.writeString(urlToWebPage)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun mapToItemAbout(): ItemAbout = ItemAbout(title, urlToImage, urlToWebPage)

    companion object CREATOR : Parcelable.Creator<ItemAboutParcelable> {
        override fun createFromParcel(parcel: Parcel): ItemAboutParcelable {
            return ItemAboutParcelable(parcel)
        }

        override fun newArray(size: Int): Array<ItemAboutParcelable?> {
            return arrayOfNulls(size)
        }
    }
}

fun ItemAbout.mapToParcelable(): ItemAboutParcelable =
    ItemAboutParcelable(title, urlToImage, urlToWebPage)