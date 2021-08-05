package com.example.criminalintent

import android.app.Application

/**
 * Created on 2021/8/5.
 *
 */
class CrimeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}