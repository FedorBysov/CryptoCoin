package com.example.cryptocoin.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimeTempToSet(timeStemp:Long?):String{
    if (timeStemp == null) return  ""
    val stemp = Timestamp(timeStemp * 1000)
    val day  = Date(stemp.time)
    val pattern = "HH:mm:ss"
    // Время по гринвичу
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(day)
}