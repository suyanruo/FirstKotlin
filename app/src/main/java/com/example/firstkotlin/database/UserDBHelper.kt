package com.example.firstkotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created on 2020/6/3.
 *
 */
class UserDBHelper(context: Context, val DB_VERSION: Int = CURRENT_VERSION):
    ManagedSQLiteOpenHelper(context,
        DB_NAME, null, DB_VERSION) {

    companion object {
        private val TAG = "UserDBHelper"
        var DB_NAME = "user.db" //数据库名称
        var TABLE_NAME = "user_info" //表名称
        var CURRENT_VERSION = 1 //当前的最新版本，如有表结构变更，该版本号要加一
        private var instance: UserDBHelper? = null
        @Synchronized
        fun getInstance(context: Context, version: Int = 0): UserDBHelper {
            if (instance == null) {
                instance = if (version > 0) UserDBHelper(
                    context,
                    version
                ) else UserDBHelper(context)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val dropSql = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropSql)
        val createSql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "_id INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL, age INTEGER NOT NULL," +
                "height LONG NOT NULL, weight FLOAT NOT NULL);"
        db?.execSQL(createSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > 1) {
            // Android的ALTER命令不支持一次添加多列，只能分多次添加
            var alterSql = "ALTER TABLE $TABLE_NAME ADD COLUMN phone VARCHAR;"
            db?.execSQL(alterSql)
            alterSql = "ALTER TABLE $TABLE_NAME ADD COLUMN password VARCHAR;"
            db?.execSQL(alterSql)
        }
    }

    fun delete(condition: String): Int {
        var count = 0
        use {
            count = delete(TABLE_NAME, condition, null)
        }
        return count
    }
}