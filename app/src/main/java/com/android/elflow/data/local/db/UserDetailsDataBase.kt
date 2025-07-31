package com.android.elflow.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.elflow.data.local.dao.UserDetailsDao
import com.android.elflow.data.local.entity.UserDetailsEntity

@Database(entities = [UserDetailsEntity::class], version = 2)
abstract class UserDetailsDataBase: RoomDatabase() {
    abstract fun usersDao(): UserDetailsDao

    companion object {
        @Volatile private var INSTANCE: UserDetailsDataBase? = null

        fun getInstance(context: Context): UserDetailsDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDetailsDataBase::class.java,
                    "email_db"
                ).fallbackToDestructiveMigration(false).build().also { INSTANCE = it }
            }
    }
}