package com.moksh.bookpedia.book.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    StringListTypeConverter::class
)
@ConstructedBy(
    BookDatabaseConstructor::class
)
abstract class FavouriteBookDatabase : RoomDatabase() {
    abstract val dao: FavouriteBookDao

    companion object {
        const val DB_NAME = "book.db"
    }
}