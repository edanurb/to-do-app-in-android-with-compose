package com.example.todoappcompose.utils

import java.util.Calendar

object CalendarUtils {
    fun getStartOfDayTimestamp(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    fun getEndOfDayTimestamp(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }
    // Get the beginning of the month in milliseconds
    fun getStartOfMonthTimestamp(): Long {
        val calendar = Calendar.getInstance()
        // Set to the first day of the current month
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        // Set the time to 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    // Get the end of the month in milliseconds
    fun getEndOfMonthTimestamp(): Long {
        val calendar = Calendar.getInstance()
        // Set to the first day of the next month
        calendar.add(Calendar.MONTH, 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        // Set the time to 00:00:00 and subtract 1 millisecond to get the last moment of the current month
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.add(Calendar.MILLISECOND, -1)
        return calendar.timeInMillis
    }
}