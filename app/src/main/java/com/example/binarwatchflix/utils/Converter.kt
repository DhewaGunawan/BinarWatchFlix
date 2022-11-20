package com.example.binarwatchflix.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Converter {
    fun splitYear(item: String): String {
       val date = item.split("-")
       return "(${date[0]})"
    }
}