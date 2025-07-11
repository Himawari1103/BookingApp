package com.nquang.bookingapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID


class Utils {
    companion object {
        fun genUUID(): String {
            return UUID.randomUUID().toString()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun localTimeToString(lcTime: LocalTime): String? {
            val dtfDate = DateTimeFormatter.ofPattern("HH:mm:ss")
            return lcTime.format(dtfDate)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun stringToLocalTime(str: String?): LocalTime? {
            val dtfDate = DateTimeFormatter.ofPattern("HH:mm:ss")
            return LocalTime.parse(str, dtfDate)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun localDateToString(lcDate: LocalDate): String? {
            val dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return lcDate.format(dtfDate)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun stringToLocalDate(str: String?): LocalDate? {
            val dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return LocalDate.parse(str, dtfDate)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun localDateTimeToString(lcDateTime: LocalDateTime): String? {
            val dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return lcDateTime.format(dtfDate)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun stringToLocalDateTime(str: String?): LocalDateTime? {
            val dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")
            return LocalDateTime.parse("$str - 00:00:00", dtfDate)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun localDateTimeToStringWithTime(lcDateTime: LocalDateTime): String? {
            val dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")
            return lcDateTime.format(dtfDate)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun stringToLocalDateTimeWithTime(str: String?): LocalDateTime? {
            val dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")
            return LocalDateTime.parse(str, dtfDate)
        }
    }
}