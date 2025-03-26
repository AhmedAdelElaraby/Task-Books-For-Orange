package com.example.books.di
import com.example.data.network.books.BooksAPI
import com.example.data.repoImpl.books.BooksRepoImpL
import com.example.domain.repo.books.BooksRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideBooksRepo(booksAPI: BooksAPI): BooksRepo {

        return BooksRepoImpL(booksAPI)
    }

}