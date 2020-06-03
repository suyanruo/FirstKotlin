package com.example.firstkotlin.util

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created on 2020/6/3.
 *
 */
class PreferenceUtil<T>(context: Context, val name: String, val default: T) :
    ReadWriteProperty<Any, T> {
    // 通过属性代理初始化共享参数对象
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("share_pre_file", Context.MODE_PRIVATE)
    }

    // 接管属性值的获取行为
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return fetchPreference(name, default)
    }

    // 接管属性值的修改行为
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        savePreference(name, value)
    }

    // 利用with函数定义临时的命名空间
    private fun <T> fetchPreference(name: String, default: T): T = with(prefs) {
        val res: Any? = when (default) {
            is Int -> getInt(name, default)
            is Long -> getLong(name, default)
            is Float -> getFloat(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        return res as T
    }

    private fun <T> savePreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Int -> putInt(name, value)
            is Long -> putLong(name, value)
            is Float -> putFloat(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
        // commit方法和apply方法都表示提交修改
    }
}