package com.example.mytestapplication.memo.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by jiyoung on 16/12/2019
 */
class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}