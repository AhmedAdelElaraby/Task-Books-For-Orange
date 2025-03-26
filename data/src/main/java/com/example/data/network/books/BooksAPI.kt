package com.example.data.network.books


import com.example.domain.entity.network.books.ResponseBooks
import com.example.domain.entity.network.details.BookDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksAPI {

    @GET("volumes")
    suspend fun Books(
        @Query("q") q: String,
        @Query("maxResults") maxResults:Int

    ): ResponseBooks




    @GET("volumes/{bookId}")
    suspend fun getBookDetails(@Path("bookId") bookId: String): BookDetailsResponse

}