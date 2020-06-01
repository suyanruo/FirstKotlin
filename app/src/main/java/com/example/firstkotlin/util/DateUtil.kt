package com.example.firstkotlin.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on 2020/5/30.
 *
 */
object DateUtil {

    val nowDateTime: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sdf.format(Date())
        }

    // 自定义时间格式
    fun getFormatTime(format: String=""): String {
        val ft: String = format
        val sdf = if (!ft.isEmpty()) SimpleDateFormat(ft)
        else SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(Date())
    }
}