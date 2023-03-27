package com.urbn.android.flickster.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CharacterEntity::class], version = 2)
abstract class DatabaseService : RoomDatabase() {



    companion object {

        private const val DATABASE_NAME = "Movie.db"

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }
    abstract fun characterDao(): CharacterDao
}