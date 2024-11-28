package com.jsj.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jsj.myapplication.data.model.Place


    @Database(entities = [Place::class], version = 1)
    abstract class AppDataBase : RoomDatabase() {
        abstract fun placeDao(): PlaceDao

        companion object {
            @Volatile
            private var INSTANCE: AppDataBase? = null

            fun getDatabase(context: Context): AppDataBase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "place"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
