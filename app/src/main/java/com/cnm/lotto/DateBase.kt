package com.cnm.lotto

import java.text.SimpleDateFormat
import java.util.*

interface DateBase {

    fun getDate(date: Date = Calendar.getInstance().time): Int = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(date).toInt()
    fun getYear(date: Date = Calendar.getInstance().time): Int = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toInt()
    fun getMonth(date: Date = Calendar.getInstance().time): Int = SimpleDateFormat("MM", Locale.KOREA).format(date).toInt()
    fun getDay(date: Date = Calendar.getInstance().time): Int = SimpleDateFormat("dd", Locale.KOREA).format(date).toInt()
}