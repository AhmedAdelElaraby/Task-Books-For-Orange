package com.example.books.ui.screens.detailsBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.utils.ApiState
import com.example.books.utils.ApiStateBook
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
class DetailsBookViewModel @Inject constructor(private val booksUseCase: BooksUseCase):ViewModel() {



    private val _books :MutableStateFlow<ApiStateBook> = MutableStateFlow<ApiStateBook>(ApiStateBook.Empty)

    val Details: StateFlow<ApiStateBook> = _books



     fun fetchDetailsBook(id:String) {
        viewModelScope.launch {
            _books.value = ApiStateBook.Loading
           booksUseCase.BooksDetails(id)
                .catch { e->
                    _books.value =  when(e){
                        is HttpException ->  ApiStateBook.Failure(convertErrorBody(e))
                        else ->  ApiStateBook.Failure(
                            ResponseError(Errors(0, emptyList(),""))
                        )
                    }
                }.collect{result ->
                   _books.value =  ApiStateBook.Success(result)
               }

        }
    }











}