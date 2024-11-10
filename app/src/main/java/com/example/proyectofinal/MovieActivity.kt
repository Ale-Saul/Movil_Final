package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.example.proyectofinal.ui.theme.AppTheme
import com.example.proyectofinal.ui.theme.onPrimaryContainerLight
import com.example.proyectofinal.ui.theme.onPrimaryLight
import com.example.proyectofinal.ui.theme.outlineLightHighContrast
import com.example.proyectofinal.ui.theme.primaryContainerLightMediumContrast
import com.example.proyectofinal.ui.theme.tertiaryCommon
import com.example.proyectofinal.viewModel.MovieViewModel

class MovieActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                    MovieScreen(
                        name = "Interestelar",
                        image = "https://m.media-amazon.com/images/S/pv-target-images/79194981293eabf6620ece96eb5a9c1fffa04d3374ae12986e0748800b37b9cf.jpg",
                        subtitle = "2014 - Ciencia ficcion",
                        points = "8.1/10",
                        descrip = "Gracias a un descubrimiento, un grupo de cientificos y " +
                                "exploradores, encabezadis por Cooper, se embarcan en un viaje " +
                                "espacial para encontrar un lugar con las condiciones necesarias" +
                                "para reemplazar a la Tierra y comenzar una nueva vida alli.",
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(name: String, image: String, subtitle: String, points:String, descrip: String) {
    val movieImage = image
    var iconSelect by remember { mutableStateOf(false) }
    var rating by remember { mutableStateOf(0) }
    val movieViewModel = MovieViewModel()
    val lifecycle = LocalLifecycleOwner.current

    fun updateUI(i: Int) {
        rating = i
    }
    movieViewModel.star.observe(
        lifecycle,
        Observer (::updateUI)
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            modifier = Modifier.size(34.dp),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
        ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(primaryContainerLightMediumContrast)
            .padding(innerPadding)
            .padding(start = 26.dp, bottom = 30.dp, end = 26.dp)
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
                    model = image,
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
                        text = points,
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
                        onRatingChanged = { newRating ->
                            movieViewModel.update(newRating)
                        }
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
    onRatingChanged: (Int) -> Unit
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
                        onRatingChanged(i)
                    },
                tint = if (i <= currentRating) tertiaryCommon else outlineLightHighContrast,
            )
        }
    }
}
