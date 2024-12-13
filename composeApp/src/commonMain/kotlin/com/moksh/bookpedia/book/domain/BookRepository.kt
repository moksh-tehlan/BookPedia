package com.moksh.bookpedia.book.domain

import com.moksh.bookpedia.core.domain.DataError
import com.moksh.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>
}