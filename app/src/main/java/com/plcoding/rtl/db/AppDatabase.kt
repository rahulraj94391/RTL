package com.plcoding.rtl.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.plcoding.rtl.db.entities.LocationHistory
import com.plcoding.rtl.db.dao.LocationHistoryDao

@Database(
    entities = [
        LocationHistory::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationHistoryDao(): LocationHistoryDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(applicationContext: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(applicationContext, AppDatabase::class.java, "rtl.db")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}