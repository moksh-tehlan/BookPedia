package com.moksh.bookpedia.book.data.network

import com.moksh.bookpedia.book.data.dto.BookWorkDto
import com.moksh.bookpedia.book.data.dto.SearchResponseDto
import com.moksh.bookpedia.core.domain.DataError
import com.moksh.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        searchQuery: String,
        resultLimit: Int? = null,
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(
        bookWorkId: String,
    ): Result<BookWorkDto, DataError.Remote>
}