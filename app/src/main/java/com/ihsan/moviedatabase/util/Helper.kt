package com.ihsan.moviedatabase.util

object Helper {
    fun convertToHoursMinutes(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = seconds % 3600 / 60
        return if (hours > 0) {
            String.format("%02d:%02d", hours, minutes)
        } else {
            String.format("%02d:%02d", minutes, seconds % 60)
        }
    }

    fun getYear(date: String): String {
        return date.substring(0, 4)
    }
}