package com.mindera.spacex.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateTimeUtils {
    companion object {

        fun getDateTimeFromMillis(milliseconds: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliseconds * 1000

            val f: NumberFormat = DecimalFormat("00")
            val day = f.format(calendar.get(Calendar.DAY_OF_MONTH)).toString()
            val month = f.format(calendar.get(Calendar.MONTH) + 1).toString()
            val year = calendar.get(Calendar.YEAR).toString()

            val hour = f.format(calendar.get(Calendar.HOUR_OF_DAY)).toString()
            val minutes = f.format(calendar.get(Calendar.MINUTE)).toString()
            val date = day.plus("-").plus(month).plus("-").plus(year)
            val time = hour.plus(":").plus(minutes)

            return date.plus(" ").plus(time)
        }

        fun getDaysDifference(launchDateMillis: Long): Long {
            val calendar = Calendar.getInstance()
            val nowMillis = calendar.timeInMillis
            val since = nowMillis > launchDateMillis // isSince or isFrom
            val diffMillis = if (since) nowMillis - launchDateMillis else launchDateMillis - nowMillis
            val modifier = if (since) 1 else -1
            return TimeUnit.MILLISECONDS.toDays(diffMillis) * modifier
        }

    }
}