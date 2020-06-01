package com.example.firstkotlin.test

import android.content.Context

/**
 * Created on 2020/5/31.
 * 数据类必须有主构造函数，入参至少一个，每个入参必须有var或val的声明。类前面不能再有其他关键字修饰。
 */
data class DataExercise(var context: Context) {
}