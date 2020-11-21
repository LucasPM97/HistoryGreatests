package com.lucas.historygreatests.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.database.daos.ChaptersDao

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Chapter::class], version = 1, exportSchema = false)
abstract class ChaptersRoomDatabase : RoomDatabase() {

    abstract fun chaptersDao(): ChaptersDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ChaptersRoomDatabase? = null

        fun getDatabase(context: Context): ChaptersRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChaptersRoomDatabase::class.java,
                    "chapters_database"
                ).build()
                
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}