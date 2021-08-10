package com.example.criminalintent.util

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import android.view.WindowInsets

/**
 * Created on 2021/8/10.
 *
 */

/**
 * 获取屏幕宽度
 * ref: https://stackoverflow.com/questions/63407883/getting-screen-width-on-api-level-30-android-11-getdefaultdisplay-and-getme
 */
fun getScreenWidth(activity: Activity): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val metrics = activity.windowManager.currentWindowMetrics
        val insets = metrics.windowInsets.getInsets(WindowInsets.Type.systemBars())
        metrics.bounds.width() - insets.left - insets.right
    } else {
        val size = Point()
        activity.windowManager.defaultDisplay.getSize(size)
        size.x
    }
}

fun getScreenHeight(activity: Activity): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val metrics = activity.windowManager.currentWindowMetrics
        val insets = metrics.windowInsets.getInsets(WindowInsets.Type.systemBars())
        metrics.bounds.height() - insets.top - insets.bottom
    } else {
        val size = Point()
        activity.windowManager.defaultDisplay.getSize(size)
        size.y
    }
}

/**
 * 根据屏幕尺寸压缩图片
 */
fun getScaledBitmap(path: String, activity: Activity): Bitmap {
    return getScaledBitmap(path, getScreenWidth(activity), getScreenHeight(activity))
}

fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap {
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true

    BitmapFactory.decodeFile(path, options)

    val srcWidth = options.outWidth.toFloat()
    val srcHeight = options.outHeight.toFloat()

    var inSampleSize = 1
    if (srcWidth > destWidth || srcHeight > destHeight) {
        val widthScale = srcWidth / destWidth
        val heightScale = srcHeight / destHeight

        val scale = if (widthScale > heightScale) {
            widthScale
        } else {
            heightScale
        }
        inSampleSize = Math.round(scale)
    }

    options = BitmapFactory.Options()
    options.inSampleSize = inSampleSize

    return BitmapFactory.decodeFile(path, options)
}