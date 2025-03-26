package com.example.books.ui.navigation

sealed class StateScreens (val route: String) {
    object HomeScreen : StateScreens("HomeScreen")
     object DetailsBooksScreen : StateScreens("DetailsBooksScreen/{bookId}"){
         fun withBookId(bookId: String) = "DetailsBooksScreen/$bookId"

     }

}