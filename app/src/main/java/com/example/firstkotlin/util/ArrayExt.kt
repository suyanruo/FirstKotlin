package com.example.firstkotlin.util

/**
 * Created on 2020/5/30.
 * 扩展方法
 */
fun <T> Array<T>.maxCustomize(greater: (T, T) -> Boolean): T? {
    var max: T? = null
    for (item in this)
        if (max == null || greater(item, max))
            max = item
    return max
}