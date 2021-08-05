package com.example.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.criminalintent.database.CrimeDatabase
import com.example.criminalintent.model.Crime
import java.lang.IllegalStateException
import java.util.*

/**
 * Created on 2021/8/5.
 *
 */
private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {
    private val database = Room.databaseBuilder(
        context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME).build()
    private val dao = database.crimeDao()

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("Not init")
        }
    }

    fun getCrimes(): LiveData<List<Crime>> = dao.getCrimes()

    fun getCrime(uuid: UUID): LiveData<Crime?> = dao.getCrime(uuid)
}