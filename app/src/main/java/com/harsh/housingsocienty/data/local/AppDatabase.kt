package com.harsh.housingsocienty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harsh.housingsocienty.data.local.dao.ForumDao
import com.harsh.housingsocienty.data.local.dao.MyUnitDao
import com.harsh.housingsocienty.data.local.dao.UpcomingEventsDao
import com.harsh.housingsocienty.model.Forum
import com.harsh.housingsocienty.model.MyUnit
import com.harsh.housingsocienty.model.UpcomingEvents

@Database(entities = [UpcomingEvents::class, Forum::class, MyUnit::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUpcomingEventDao(): UpcomingEventsDao
    abstract fun getForumDao(): ForumDao
    abstract fun getMyUnitDao(): MyUnitDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "society_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE!!
            } else
                INSTANCE!!
        }
    }
}