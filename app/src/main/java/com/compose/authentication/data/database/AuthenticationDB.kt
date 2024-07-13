package com.compose.authentication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.compose.authentication.data.model.Authentication

@Database(entities = [Authentication::class], version = 1)
abstract class AuthenticationDB : RoomDatabase() {

    abstract fun authenticationDao(): AuthenticationDao

    companion object {

        @Volatile
        private var INSTANCE: AuthenticationDB? = null

        fun getDB(context: Context): AuthenticationDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AuthenticationDB::class.java,
                    "auth_db.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}