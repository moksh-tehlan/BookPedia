package com.moksh.bookpedia.book.domain

import com.moksh.bookpedia.core.domain.DataError
import com.moksh.bookpedia.core.domain.EmptyResult
import com.moksh.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>
    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id:String) : Flow<Boolean>
    suspend fun markAsFavourite(book:Book) : EmptyResult <DataError.Local>
    suspend fun deleteFavourites(id:String) 
}