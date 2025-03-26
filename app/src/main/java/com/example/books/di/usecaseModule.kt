package com.example.books.di


import com.example.domain.repo.books.BooksRepo
import com.example.domain.usecase.books.BooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object usecaseModule {


    @Provides
    fun  BooksUseCases(booksRepo: BooksRepo): BooksUseCase {

        return BooksUseCase(booksRepo)

    }
//
//    @Provides
//    fun ProvideCategoryUseCase(repo: Categoryrepo): Categorys {
//
//        return Categorys(repo)
//
//    }
//
//    @Provides
//    fun ProvideProductUseCase(repo: ProductRepo): Products {
//
//        return Products(repo)
//
//    }
//
//    @Provides
//    fun ProvideAdsUseCase(repo: AdsRepo): AdsHome {
//
//        return AdsHome(repo)
//
//    }
//
//    @Provides
//    fun ProvideLoginUseCase(repo: LoginRepo): Login {
//
//        return Login(repo)
//
//    }
//
//    @Provides
//    fun ProvideFavorateUseCase(repo: FavorateRepo): Favorate {
//
//        return Favorate(repo)
//
//    }
//
//    @Provides
//    fun ProvideCartUseCase(repo: CartRepo): CartUsecase {
//
//        return CartUsecase(repo)
//
//    }
//
//    @Provides
//    fun ProvideDetilseProductUseCase(repo: ProductDetilseRepo): ProductsDetilse {
//
//        return ProductsDetilse(repo)
//
//    }
//
//
//    @Provides
//    fun ProvideTokenUseCase(token: RefreshTokenRepo): Token {
//
//        return Token(token)
//
//    }
//
//
//    @Provides
//    fun ProvideSearchUseCase(search: SearchRepo): Search {
//
//        return Search(search)
//
//    }
//
//    @Provides
//    fun ProvideSFilterUseCase(search: FilterRepo): Filter {
//
//        return Filter(search)
//
//    }




}