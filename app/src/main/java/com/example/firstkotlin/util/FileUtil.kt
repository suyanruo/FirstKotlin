package com.example.firstkotlin.util

import android.content.Context
import java.io.File

/**
 * Created on 2020/6/4.
 *
 */
object FileUtil {

    fun deleteFile(
        context: Context,
        file: File?
    ) {
        if (file == null || !file.exists()) {
            return
        }
        if (file.isFile) {
            file.delete()
            return
        }
        if (file.isDirectory) {
            val files = file.listFiles()
            if (files != null) {
                for (f in files) {
                    f.delete()
                }
            }
            file.delete()
        }
    }
}