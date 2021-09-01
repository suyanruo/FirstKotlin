package com.example.firstkotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firstkotlin.database.dao.UserDao
import com.example.firstkotlin.database.entity.User

/**
 * Created on 2021/8/23.
 *
 */
@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}