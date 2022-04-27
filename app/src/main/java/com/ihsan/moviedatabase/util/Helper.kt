package com.ihsan.moviedatabase.util

object Helper {
    fun convertToHoursMinutes(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = seconds % 3600 / 60
        return if (hours > 0) {
            String.format("%02dh %02dm", hours, minutes)
        } else {
            String.format("%02dh %02dm", minutes, seconds % 60)
        }
    }

    fun getYear(date: String): String {
        return date.substring(0, 4)
    }
}