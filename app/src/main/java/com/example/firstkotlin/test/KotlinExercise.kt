package com.example.firstkotlin.test

import android.content.Context
import com.example.firstkotlin.util.DateUtil
import com.example.firstkotlin.util.getCurrentDate
import com.example.firstkotlin.util.maxCustomize
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created on 2020/5/30.
 * 类中带输入参数来声明主构造函数时，如果没有@符号，constructor可省略。
 * 声明 @JvmOverloads 表示如果构造方法中有默认参数时（即指定默认值）可以在jave类中使用该构造方法可以不传默认参数对应的变量。
 * 对构造函数中的参数声明var、val就能创造同名的成员变量。
 * 声明open表示可被继承。
 */
open class KotlinExercise @JvmOverloads constructor(var context: Context, var name: String = "zj") {
    // 主构造函数的方法体。类名中的参数为主构造函数的输入参数。可以不声明主构造函数
    init {
        context.toast("这是kotlin测试类")
    }

    // 二级构造函数。如果显式声明了主构造函数，在主构造函数调用完后才可能会调用
    constructor(context: Context, name: String, sex: String): this(context, name) {
        context.toast("这是${name}(${sex})的kotlin测试类")
    }

    open fun test() {
        // 使用Date的扩展方法
        Date().getCurrentDate()

        // 使用Array的扩展方法
        val stringA = arrayOf("abc", "de", "fg")
        stringA.maxCustomize{a, b -> a.length > b.length}

        // 单例对象
        DateUtil.nowDateTime

        KotlinExercise(context, "zj")
        KotlinExercise.KotlinExerciseCom.staticFun()

        val secondExercise = SecondExercise(context, "zj", "male")
        val nestedClass = SecondExercise.NestedClass() // 用类来访问
        val innerClass = secondExercise.InnerClass() // 用对象来访问
    }

    /**
     * 伴生对象。使用companion object声明该对象，伴生对象的方法相当于java中的静态方法
     * 在外部可以直接使用KotlinExerciseCom.staticFun()访问该伴生对象内部的方法
      */

    companion object KotlinExerciseCom {
        // 静态成员
        val MALE = 0
        val FEMALE = 1

        fun staticFun() {

        }
    }
}