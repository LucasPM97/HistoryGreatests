package com.lucas.historygreatests.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.database.daos.BooksDao

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BooksRoomDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BooksRoomDatabase? = null

        fun getDatabase(context: Context): BooksRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksRoomDatabase::class.java,
                    "books_database"
                ).build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}