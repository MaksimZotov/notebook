package com.maksimzotov.notebook.presenter.parcelable

import android.os.Parcel
import android.os.Parcelable
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.note.NoteWithAlarm
import com.maksimzotov.notebook.presenter.main.util.Constants.EMPTY_STRING
import java.util.*

class NoteWithAlarmParcelable(
    _id: Int,
    title: String,
    text: String,
    time: Date,
    private val timeToNotify: Date
): NoteParcelable(_id, title, text, time) {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: EMPTY_STRING,
        parcel.readString() ?: EMPTY_STRING,
        parcel.readDate() ?: Date(0),
        parcel.readDate() ?: Date(0)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeDate(timeToNotify)
    }

    override fun describeContents(): Int = 0

    override fun mapToNote(): Note = NoteWithAlarm(_id, title, text, time, timeToNotify)

    companion object CREATOR : Parcelable.Creator<NoteWithAlarmParcelable> {
        override fun createFromParcel(parcel: Parcel): NoteWithAlarmParcelable {
            return NoteWithAlarmParcelable(parcel)
        }

        override fun newArray(size: Int): Array<NoteWithAlarmParcelable?> {
            return arrayOfNulls(size)
        }
    }
}

fun NoteWithAlarm.mapToParcelable(): NoteParcelable =
    NoteWithAlarmParcelable(_id, title, text, time, timeToNotify)