package com.example.firstkotlin.model

/**
 * Created on 2020/5/28.
 *
 */
data class User(var firstName: String, var lastName: String, var age: Int = 18, val sex: String = "保密")