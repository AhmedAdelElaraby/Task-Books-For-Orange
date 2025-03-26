package com.example.data.repoImpl.books

import com.example.data.network.books.BooksAPI
import com.example.domain.entity.network.details.BookDetailsResponse
import com.example.domain.repo.books.BooksRepo


class BooksRepoImpL(private val booksAPI:BooksAPI) :BooksRepo{
    override suspend fun Books(q: String,maxResults:Int) = booksAPI.Books(q,maxResults)
    override suspend fun getBookDetails(id: String) = booksAPI.getBookDetails(id)

}