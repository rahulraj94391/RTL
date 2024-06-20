package com.plcoding.rtl.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateTimeUtil {
    companion object {
        private fun convertMillisToIST(millis: Long): String {
            val instant = Instant.ofEpochMilli(millis)
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Kolkata"))
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return dateTime.format(formatter)
        }
        
        fun getCurrentTime() = convertMillisToIST(System.currentTimeMillis())
    }
}