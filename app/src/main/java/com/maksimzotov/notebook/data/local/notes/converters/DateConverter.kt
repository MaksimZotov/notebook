package com.maksimzotov.notebook.data.local.notes.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DateConverter {
    private val gson = Gson()
    private val typeDate = object : TypeToken<Date>() {}.type

    @TypeConverter
    fun fromDate(date: Date): String =
        gson.toJson(date)

    @TypeConverter
    fun toDate(date: String): Date =
        gson.fromJson(date, typeDate)
}