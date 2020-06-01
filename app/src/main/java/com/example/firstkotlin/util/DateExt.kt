package com.example.firstkotlin.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on 2020/5/30.
 * 扩展方法
 */
fun Date.getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    return dateFormat.format(this)
}

fun Date.getCurrentTime(): String {
    val timeFormat = SimpleDateFormat("HH:mm:ss")
    return timeFormat.format(this)
}

fun Date.getCurrentDateTime(): String {
    val timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return timeFormat.format(this)
}

// 自定义时间格式
fun Date.getFormatTime(format: String=""): String {
    var ft: String = format
    val sdf = if (!ft.isEmpty()) SimpleDateFormat(ft)
    else SimpleDateFormat("yyyyMMddHHmmss")
    return sdf.format(this)
}