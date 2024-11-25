package com.example.proyectofinal.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.example.network.MovieRemoteDataSource
import com.example.network.RetrofitBuilder
import com.example.proyectofinal.viewModel.MovieHomeViewModel
import com.example.proyectofinal.viewModel.MovieViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.theme.onPrimaryContainerLight
import com.example.proyectofinal.ui.theme.onPrimaryLight
import com.example.proyectofinal.ui.theme.outlineLightHighContrast
import com.example.proyectofinal.ui.theme.primaryContainerLightMediumContrast
import com.example.proyectofinal.ui.theme.tertiaryCommon
import com.example.repository.MovieRepository

@Composable
fun MovieDetailScreen(movieId: Int?, onBackPressed: () -> Unit) {
    val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(RetrofitBuilder)
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val repository = MovieRepository(context)
    val moviesHomeViewModel: MovieHomeViewModel = MovieHomeViewModel(repository, dataSource)

    val movie = movieId?.let { moviesHomeViewModel.getMovieById(it).observeAsState() }?.value
    movie?.let {
        DetailContentScreen(
            keyMovie = it.movieId,
            name = it.title,
            image = it.posterPath,
            subtitle = "2014 - Ciencia ficcion",
            points = it.voteAverage,
            descrip = it.description,
            onBackPressed = onBackPressed
        )
    }?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Cargando la pelicula")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContentScreen(keyMovie: Int,name: String, image: String, subtitle: String, points:Double, descrip: String, onBackPressed: () -> Unit) {
    val movieImage = image
    var iconSelect by remember { mutableStateOf(false) }
    var rating by remember { mutableStateOf(0) }
    val repository = MovieRepository(LocalContext.current)
    val movieViewModel = MovieViewModel(repository)
    val lifecycle = LocalLifecycleOwner.current

    fun updateUI(i: Int) {
        rating = i
    }
    movieViewModel.star.observe(
        lifecycle,
        Observer(::updateUI)
    )
    fun updateUIFav(b: Boolean) {
        iconSelect = b
    }
    movieViewModel.icon_favorite.observe(
        lifecycle,
        Observer(::updateUIFav)
    )
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                colors = TopAppBarDefaults.topAppBarColors(primaryContainerLightMediumContrast),
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            modifier = Modifier.size(34.dp),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {innerPadding ->
        Box(modifier = Modifier
            //.fillMaxSize()
            .background(primaryContainerLightMediumContrast)
            .padding(innerPadding)
            .padding(top = 1.dp, start = 26.dp, bottom = 30.dp, end = 26.dp)
            .clip(RoundedCornerShape(25.dp))
        ){
            Column(
                modifier = Modifier
                    .background(onPrimaryContainerLight)
                    .fillMaxSize()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${image}",
                    contentDescription = null,
                    modifier= Modifier
                        .height(340.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = onPrimaryLight
                    )
                    IconButton(
                        onClick = {
                            movieViewModel.add_favorite(iconSelect)
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            imageVector = if (iconSelect) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favoritos",
                            modifier = Modifier.size(34.dp),
                            tint = onPrimaryLight
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = onPrimaryLight,
                        fontSize = 15.sp
                    )
                    Text(
                        text = String.format("%.1f", points),
                        style = MaterialTheme.typography.bodyMedium,
                        color = tertiaryCommon,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        rating = rating,
                        onRatingChanged = { newRating, movieID ->
                            movieViewModel.update(newRating, movieID)
                        },
                        movieID = keyMovie
                    )
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .padding(start = 10.dp)
                            .height(40.dp),
                        border = BorderStroke(1.dp, onPrimaryLight),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_ver_comentarios),
                            color = onPrimaryLight,
                            fontSize = 16.sp
                        )
                    }
                }

                Text(
                    text = descrip,
                    color = onPrimaryLight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    onRatingChanged : (Int, Int) -> Unit,
    movieID: Int
) {
    var currentRating by remember { mutableStateOf(rating) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..5) {
            Icon(
                imageVector =
                if (i <= currentRating) {
                    Icons.Filled.Star
                } else {
                    Icons.Outlined.Star
                },
                contentDescription = "Rating",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        currentRating = i
                        onRatingChanged(i, movieID)
                    },
                tint = if (i <= currentRating) tertiaryCommon else outlineLightHighContrast,
            )
        }
    }
}

