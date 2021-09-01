package com.example.firstkotlin.database

import android.content.Context
import androidx.room.Room

/**
 * Created on 2021/8/23.
 *
 */
private const val DATABASE_NAME = "first-kotlin-database"

class DbRepository private constructor(context: Context) {
    companion object {
        private var INSTANCE: DbRepository? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DbRepository(context)
            }
        }

        fun getInstance(): DbRepository {
            return INSTANCE!!
        }
    }

    private val database = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val dao = database.userDao()
}