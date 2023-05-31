package com.example.weatherforecastapp.domain.ui.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class DateUtils {
    companion object {
        fun unixDay(timestamp: Long): String? {
            val date = Date(timestamp * 1000L)
            val dateFormat = SimpleDateFormat("EEEE", Locale.GERMANY)
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(date)
        }
    }
}