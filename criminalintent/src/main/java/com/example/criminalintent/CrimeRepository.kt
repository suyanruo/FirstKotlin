package com.example.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.criminalintent.database.CrimeDatabase
import com.example.criminalintent.model.Crime
import com.example.criminalintent.model.migration1_2
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

/**
 * Created on 2021/8/5.
 *
 */
private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {
    private val database = Room.databaseBuilder(
        context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME)
        .addMigrations(migration1_2)
        .build()
    private val dao = database.crimeDao()
    private val executor = Executors.newSingleThreadExecutor()

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

    fun updateCrime(crime: Crime) {
        executor.execute {
            dao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {
        executor.execute {
            dao.insertCrime(crime)
        }
    }
}