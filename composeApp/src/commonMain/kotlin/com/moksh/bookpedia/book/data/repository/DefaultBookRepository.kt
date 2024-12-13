package com.moksh.bookpedia.book.data.repository

import com.moksh.bookpedia.book.data.mapper.toBook
import com.moksh.bookpedia.book.data.network.RemoteBookDataSource
import com.moksh.bookpedia.book.domain.Book
import com.moksh.bookpedia.book.domain.BookRepository
import com.moksh.bookpedia.core.domain.DataError
import com.moksh.bookpedia.core.domain.Result
import com.moksh.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(
        query: String,
    ): Result<List<Book>, DataError.Remote> {
        return remoteDataSource.searchBooks(query)
            .map { it -> it.results.map { it.toBook() } }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return remoteDataSource.getBookDetails(bookId).map {
            it.description
        }
    }
}