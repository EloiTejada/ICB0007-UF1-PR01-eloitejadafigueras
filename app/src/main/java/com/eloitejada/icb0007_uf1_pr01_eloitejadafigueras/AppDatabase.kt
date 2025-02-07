package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database
(entities = [Rocket::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rocketDao(): RocketDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "rocket_db")
                        .build()
                }

            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
