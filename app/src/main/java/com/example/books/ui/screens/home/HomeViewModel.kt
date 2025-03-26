package com.example.books.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.example.books.utils.ApiState
import com.example.books.utils.ErrorResponse.Errors
import com.example.books.utils.ErrorResponse.ResponseError
import com.example.domain.usecase.books.BooksUseCase
import com.mediaheist.dizzly.ErrorResponse.convertErrorBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor( private val booksUseCase: BooksUseCase):ViewModel() {



    private val _books :MutableStateFlow<ApiState> = MutableStateFlow<ApiState>(ApiState.Empty)

    val books: StateFlow<ApiState> = _books

    init {
        fetchBooks("*",40)
    }

     fun fetchBooks(q:String,maxResults:Int) {
        viewModelScope.launch {
            _books.value = ApiState.Loading
           booksUseCase.Books(q,maxResults)
                .catch { e->
                    _books.value =  when(e){
                        is HttpException ->  ApiState.Failure(convertErrorBody(e))
                        else ->  ApiState.Failure(
                            ResponseError(Errors(0, emptyList(),""))
                        )
                    }
                }.collect{result ->
                   _books.value =  ApiState.Success(result)
               }

        }
    }











}