package com.example.firstkotlin.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created on 2020/6/2.
 *
 */
object SharedUtil {

    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("share_pre_file", Context.MODE_PRIVATE)
    }

    fun saveString(context: Context, key: String, value: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun fetchString(context: Context, key: String): String? {
        return getSharedPreferences(context).getString(key, "")
    }
}