package com.example.books.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest


import com.example.books.R
import com.example.books.ui.navigation.StateScreens
import com.example.books.utils.ApiState
import com.example.domain.entity.network.books.Item
import com.google.android.material.search.SearchBar


@Composable
fun HomeScreen(navController: NavController,viewModel: HomeViewModel = hiltViewModel()) {

    val books by viewModel.books.collectAsStateWithLifecycle()





    Column(modifier = Modifier.background(color = Color.White)) {
        SearchBar { query ->
            if (query.isNullOrEmpty()){
                viewModel.fetchBooks("*",40)
            }else {
                viewModel.fetchBooks(query,40)

            }
        }
        when (books) {
            is ApiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is ApiState.Success -> {
                val books = (books as ApiState.Success).data.items
                BookGridScreen(books,navController)
            }


            is ApiState.Failure -> {
                Text(
                    text = "${
                        (books as ApiState.Failure).e?.error?.message}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black),
                    modifier = Modifier.padding(bottom = 5.dp, top = 5.dp),
                    textAlign = TextAlign.Start
                )
            }

            else -> {}
        }
    }}


@Composable
fun SearchBar(onTextChanged: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { query ->
            text = query
            onTextChanged(query)
        },
        placeholder = {
            Text(
                "ابحث عن كتاب...",
                style = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
        },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "بحث") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BookGridScreen(listBooks: List<Item>, navController: NavController) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(8.dp),
        contentPadding = PaddingValues(2.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(listBooks) { book ->
            BookItem(book) {
                navController.navigate(StateScreens.DetailsBooksScreen.withBookId(book.id))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookItem(item: Item?, onClick: () -> Unit) {
    val secureUrl = item?.volumeInfo?.imageLinks?.smallThumbnail
        ?.replace("http://", "https://")
        ?: "https://your-default-image-url.com/default.jpg"
    val authorsText = item?.volumeInfo?.authors?.joinToString(", ") ?: "غير معروف"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .fillMaxHeight(), onClick = {
            onClick()
        },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),

            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(secureUrl)
                    .crossfade(true)
                    .allowHardware(false)
                    .build(),
                contentDescription = "غلاف الكتاب",
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item?.volumeInfo?.subtitle ?: " غير معروف ",
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp, color = Color.Black),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Text(
                text = "${authorsText}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier.padding(bottom = 5.dp, top = 5.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}