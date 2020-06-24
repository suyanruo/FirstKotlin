package com.example.firstkotlin.model

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt


/**
 * Created on 2020/6/7.
 *
 */
data class Person(var name: ObservableField<String>, var age: ObservableInt, var imageUrl: Int)