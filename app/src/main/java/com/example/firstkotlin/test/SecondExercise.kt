package com.example.firstkotlin.test

import android.content.Context
import org.jetbrains.anko.toast

/**
 * Created on 2020/5/31.
 * 继承自父类的子类，原有的输入参数不必加var或val，新增的参数才需要加
 */
class SecondExercise(context: Context, name: String, var sex: String):KotlinExercise(context, name) {

    override fun test() {
    }

    class NestedClass {
        // 嵌套类不能访问外部类的成员变量和方法
    }

    inner class InnerClass {
        // 内部类可以使用外部类的成员变量和方法
        fun test() {
            context.toast("调用外部类的context")
        }
    }
}