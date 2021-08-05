package com.example.criminalintent.database

import androidx.room.TypeConverter
import java.util.*

/**
 * Created on 2021/8/5.
 *
 */
class CrimeTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(million: Long?): Date? {
        return million?.let {
            Date(million)
        }
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
}