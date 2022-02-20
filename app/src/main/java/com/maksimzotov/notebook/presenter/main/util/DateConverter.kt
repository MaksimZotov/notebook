package com.maksimzotov.notebook.presenter.main.util

import android.annotation.SuppressLint
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {

    private val pattern = "dd.MM.yyyy"

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(pattern)

    fun format(date: Date): String = dateFormat.format(date)
    fun parse(date: String): Date =
        try {
            dateFormat.parse(date)!!
        } catch (ex: NullPointerException) {
            throw IllegalArgumentException(
                "Invalid date $date does not match to the pattern \"$pattern\""
            )
        }

    fun getTextByYearMonthDay(day: Int, month: Int, year: Int): String =
        String.format("%02d.%02d.%d", day, month, year)

    fun getDayMonthYearByText(date: CharSequence): List<Int> =
        date.split('.').map { it.toInt() }
}