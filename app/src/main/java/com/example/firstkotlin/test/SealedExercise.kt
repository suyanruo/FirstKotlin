package com.example.firstkotlin.test

/**
 * Created on 2020/5/31.
 *
 */
sealed class SealedExercise {
    // 密封类相当于严格的枚举类
    class Spring (var name:String) : SealedExercise()
    class Summer (var name:String) : SealedExercise()
    class Autumn (var name:String) : SealedExercise()
    class Winter (var name:String) : SealedExercise()
}