package com.moksh.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.moksh.bookpedia.book.data.database.FavouriteBookDao
import com.moksh.bookpedia.book.data.mapper.toBook
import com.moksh.bookpedia.book.data.mapper.toBookEntity
import com.moksh.bookpedia.book.data.network.RemoteBookDataSource
import com.moksh.bookpedia.book.domain.Book
import com.moksh.bookpedia.book.domain.BookRepository
import com.moksh.bookpedia.core.domain.DataError
import com.moksh.bookpedia.core.domain.EmptyResult
import com.moksh.bookpedia.core.domain.Result
import com.moksh.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteDataSource: RemoteBookDataSource,
    private val favouriteBookDao : FavouriteBookDao,
) : BookRepository {
    override suspend fun searchBooks(
        query: String,
    ): Result<List<Book>, DataError.Remote> {
        return remoteDataSource.searchBooks(query)
            .map { it -> it.results.map { it.toBook() } }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favouriteBookDao.getFavouriteBook(bookId)
        return if(localResult == null){
            remoteDataSource.getBookDetails(bookId).map {
                it.description
            }
        }else{
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favouriteBookDao
            .getFavouriteBooks()
            .map { bookEntities ->
                 bookEntities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favouriteBookDao
            .getFavouriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavourite(book: Book): EmptyResult<DataError.Local> {
        return try{
            favouriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        }catch (e:SQLiteException){
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFavourites(id: String) {
        favouriteBookDao.deleteFavouriteBook(id)
    }
}