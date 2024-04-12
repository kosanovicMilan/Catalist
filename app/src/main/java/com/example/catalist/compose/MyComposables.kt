package com.example.catalist.compose

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.catalist.uimodel.CatUIData
import com.example.catalist.ui.theme.CatalistTheme

@Composable
fun RemoteImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: androidx.compose.ui.layout.ContentScale = androidx.compose.ui.layout.ContentScale.Crop,
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {

        }).build()
    )
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun BreedImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: androidx.compose.ui.layout.ContentScale = androidx.compose.ui.layout.ContentScale.Crop,
) {
    Box(
        modifier = modifier
            .background(Color(81,80,150,255), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {

            }).build()
        )
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = contentScale
        )
    }
}


@Composable
fun AppIconButton(
    modifier: Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
){
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
    }
}
@Composable
fun CatListItem(
    cat: CatUIData,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(139, 179, 175, 255)
        ),
        modifier = Modifier
            .padding(20.dp, 5.dp, 20.dp, 10.dp)
            .clickable {
                onClick()
                Log.d("macaClick","klikno na karticu")
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        )
    ) {

        val list = cat.temperament.split(",")
        Column(
        ) {

//                cat.image_url?.let {
                    RemoteImage(
                        url = "https://cdn2.thecatapi.com/images/_6x-3TiCA.jpg",
                        contentDescription = "Cat Image",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                //}

            Row(modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)) {
                    Text(
                        text = cat.name,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Column {
                        val descShort = cat.description.split(".")
                        val oneDesc = descShort[0];

                        Text(text = oneDesc)

                        Row(
                             modifier = Modifier.fillMaxWidth()

                            ) {
                            list.take(3).forEach {
                                AssistChip(
                                    modifier = Modifier.weight(1f).padding(2.dp),
                                    onClick = { },
                                    label = {
                                        Text(
                                        it,
                                        modifier = Modifier,
                                        fontSize = 8.sp
                                        ) }
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
@Composable
fun RotatingImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    // Koristimo rememberInfiniteTransition za kontinuiranu rotaciju
    val infiniteTransition = rememberInfiniteTransition()

    // Definišemo animaciju za rotaciju
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Prikažemo sliku sa animacijom rotacije
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .rotate(rotation) // Rotiramo sliku
    )
}

@Preview(showBackground = true)
@Composable
fun CardPreview(){
    CatalistTheme {

        CatListItem(cat = CatUIData("1","12 - 20","Mily Ramondy","The dangerous animal on planet, love to play with tennis ball.They love night walks by the beech",
            "SRB", life_span = "10 - 25","Gooddddddddddd,Better,Nice,Angry,Cold",5,4,4,2,3,5,
                   1,"nesto","nesto"

        ),
            onClick = {})
    }
}