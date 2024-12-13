package com.moksh.bookpedia.book.data.network

import com.moksh.bookpedia.book.data.dto.BookWorkDto
import com.moksh.bookpedia.book.data.dto.SearchResponseDto
import com.moksh.bookpedia.core.data.safeCall
import com.moksh.bookpedia.core.domain.DataError
import com.moksh.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteDataSource(
    private val httpClient: HttpClient,
) : RemoteBookDataSource {
    override suspend fun searchBooks(
        searchQuery: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall <SearchResponseDto>{
            httpClient.get("$BASE_URL/search.json") {
                parameter("q", searchQuery)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,language,cover_i,author_name,author_key,cover_edition_key,first_publish_year,ratings_average,ratings_count,number_of_pages_median,edition_count"
                )
            }
        }
    }

    override suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote> {
        return safeCall <BookWorkDto>{
            httpClient.get("$BASE_URL/works/$bookWorkId.json")
        }
    }
}