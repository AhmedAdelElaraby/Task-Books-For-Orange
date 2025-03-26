package com.example.books.ui.screens.detailsBooks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.books.utils.ApiState
import com.example.books.utils.ApiStateBook
import com.example.domain.entity.network.details.BookDetailsResponse


@Preview(showBackground = true)
@Composable
fun DetailsBooksScreen(navController: NavController, bookId: String, viewModel: DetailsBookViewModel = hiltViewModel()) {

    LaunchedEffect(bookId) {
        viewModel.fetchDetailsBook(bookId)
    }

    val bookDetails by viewModel.Details.collectAsState()


    when (bookDetails) {

        is ApiStateBook.Loading ->{

        }
        is ApiStateBook.Success -> {
            val books = (bookDetails as ApiStateBook.Success).data
            DetailsItems(books)
        }
        is ApiStateBook.Failure->{
            Text(
                text = "${
                    (bookDetails as ApiState.Failure).e?.error?.message}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier.padding(bottom = 5.dp, top = 5.dp),
                textAlign = TextAlign.Start
            )
        }


        else -> {}
    }





}



@Preview(showBackground = true)
@Composable
fun DetailsItems(data: BookDetailsResponse){





        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {

            AsyncImage(
                model = data.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"),
                contentDescription = "غلاف الكتاب",
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = data.volumeInfo.title,Modifier.padding(start = 5.dp), fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = "المؤلف: ${data.volumeInfo.authors?.joinToString(", ")}",Modifier.padding(start = 5.dp) ,color = Color.Gray)
            Text(text = "سنة النشر: ${data.volumeInfo.publishedDate}",Modifier.padding(start = 5.dp), fontSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "الوصف",Modifier.padding(start = 5.dp) ,fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(modifier = Modifier.padding(start = 5.dp),text = data.volumeInfo.title ?: "لا يوجد وصف متاح")
        }




}