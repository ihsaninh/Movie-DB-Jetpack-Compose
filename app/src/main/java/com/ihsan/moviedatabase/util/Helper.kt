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

    fun formatCompactCurrency(value: Float): String {
        val currency = String.format("%.0f", value)
        val length = currency.length
        return when {
            length < 4 -> currency
            length < 7 -> currency.substring(0, length - 3) + "K"
            length < 10 -> currency.substring(0, length - 6) + "M"
            length < 13 -> currency.substring(0, length - 9) + "B"
            else -> currency.substring(0, length - 12) + "T"
        }
    }
}