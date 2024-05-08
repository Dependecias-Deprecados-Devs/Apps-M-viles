package com.example.moviecompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviecompose.data.model.Movies
import com.example.moviecompose.data.remote.ApiClient
import com.example.moviecompose.data.repositories.MovieRepository

@OptIn(ExperimentalMaterial3Api::class) // Suprime el error del DropDownMenu experimental
@Composable
fun MovieList(movieRepository: MovieRepository = MovieRepository()) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val endpoints = listOf("upcoming", "popular", "top_rated")
    val endpoint = remember {
        mutableStateOf(endpoints[0])
    }
    val movies = remember {
        mutableStateOf(emptyList<Movies>())
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Box {
                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = {
                        expanded.value = !expanded.value
                    }) {
                    OutlinedTextField(
                        value = endpoint.value,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded.value
                            )
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        endpoints.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    endpoint.value = it
                                    expanded.value = false
                                    movieRepository.getMovies(endpoint.value) { items ->
                                        movies.value = items
                                    }
                                }
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {
                    movieRepository.getMovies(endpoint.value) { items ->
                        movies.value = items
                    }
                }
            ) {
                Text(text = "Show")
            }
            LazyColumn {    //Para listados dinamicos
                items(movies.value) { movie ->
                    MovieItem(
                        movie,
                        insert = { movieRepository.insert(movie) },
                        delete = { movieRepository.delete(movie) }
                    )
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movies,
    insert: (() -> Unit)? = null,
    delete: (() -> Unit)? = null
) {
    val isFavorite = remember {
        mutableStateOf(false)
    }

    isFavorite.value = movie.isFavorite
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                modifier = Modifier.size(92.dp),
                imageModel = {
                    ApiClient.getUrl(movie.posterPath)
                })
            Text(
                modifier = Modifier
                    .weight(3F)
                    .padding(4.dp),
                text = movie.title
            )
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    isFavorite.value = !isFavorite.value
                    movie.isFavorite = isFavorite.value
                    if (movie.isFavorite) insert?.let { it() } else delete?.let { it() }
                }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = if (isFavorite.value)
                        MaterialTheme.colorScheme.primary
                    else Color.Gray
                )
            }
        }
    }
}