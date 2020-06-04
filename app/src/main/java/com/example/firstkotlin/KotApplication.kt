package com.example.firstkotlin

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created on 2020/6/3.
 *
 */
class KotApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: KotApplication by Delegates.notNull()

        fun instance() = instance
    }
}