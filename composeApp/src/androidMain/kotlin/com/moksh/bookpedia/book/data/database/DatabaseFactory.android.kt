package com.moksh.bookpedia.book.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseFactory(
    private val context:Context
) {
    actual fun create(): RoomDatabase.Builder<FavouriteBookDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(FavouriteBookDatabase.DB_NAME)
        return Room.databaseBuilder<FavouriteBookDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}