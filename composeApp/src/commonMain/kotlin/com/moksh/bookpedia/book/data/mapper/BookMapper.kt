package com.moksh.bookpedia.book.data.mapper

import com.moksh.bookpedia.book.data.database.BookEntity
import com.moksh.bookpedia.book.data.dto.SearchedBookDto
import com.moksh.bookpedia.book.domain.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        languages = languages ?: emptyList(),
        description = null,
        averageRating = averageRating,
        ratingsCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions ?: 0,
        publishedYear = firstPublishYear.toString(),
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = publishedYear,
        ratingAverage = averageRating,
        ratingsCount = ratingsCount,
        numPagesMedian = numPages,
        numEditions = numEditions,
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        publishedYear = firstPublishYear,
        averageRating = ratingAverage,
        ratingsCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions,
    )
}