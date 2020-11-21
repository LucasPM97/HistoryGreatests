package com.lucas.historygreatests.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.database.daos.TopicsDao

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Topic::class], version = 1, exportSchema = false)
abstract class TopicsRoomDatabase : RoomDatabase() {

    abstract fun topicsDao(): TopicsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TopicsRoomDatabase? = null

        fun getDatabase(context: Context): TopicsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TopicsRoomDatabase::class.java,
                    "topics_database"
                ).build()
                
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}