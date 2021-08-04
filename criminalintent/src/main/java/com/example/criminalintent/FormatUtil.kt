package com.example.criminalintent

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on 2021/8/4.
 * 对数据进行格式化处理
 *
 */
fun formatDate(date: Date): String {
    val pattern = "yyyy MM dd  HH:mm:ss"
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(date)
}