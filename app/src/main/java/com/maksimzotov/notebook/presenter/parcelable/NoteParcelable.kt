package com.maksimzotov.notebook.presenter.parcelable

import android.os.Parcel
import android.os.Parcelable
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.presenter.main.util.Constants.EMPTY_STRING
import java.util.*

open class NoteParcelable(
    val _id: Int,
    val title: String,
    val text: String,
    val time: Date
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: EMPTY_STRING,
        parcel.readString() ?: EMPTY_STRING,
        parcel.readDate() ?: Date(0),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeString(title)
        parcel.writeString(text)
        parcel.writeDate(time)
    }

    override fun describeContents(): Int = 0

    open fun mapToNote(): Note = Note(_id, title, text, time)

    companion object CREATOR : Parcelable.Creator<NoteParcelable> {
        override fun createFromParcel(parcel: Parcel): NoteParcelable {
            return NoteParcelable(parcel)
        }

        override fun newArray(size: Int): Array<NoteParcelable?> {
            return arrayOfNulls(size)
        }
    }
}

fun Note.mapToParcelable(): NoteParcelable =
    NoteParcelable(_id, title, text, time)