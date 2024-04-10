package com.example.catalist

import androidx.compose.foundation.Image

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward

import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catalist.ui.theme.CatalistTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsListScreen(
    cats : List<CatData>,
    onCatClick: (CatData) -> Unit,
    onSearchClick: () -> Unit,
) {
        Scaffold (

            topBar = {
                Column(

                ) {
                    CenterAlignedTopAppBar(title = { Text(text = "CatsList")})
                    Divider()
                }
            },

            floatingActionButton = {

                FloatingActionButton(
                    onClick = onSearchClick,
                    containerColor = Color.Yellow

                ) {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "searchButton"
                    )

                }

            },
            content = {
                paddingValues ->


                Column (
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))


                            cats.forEach {
                                Column {
                                    key(it.id) {
                                        CatListItem(
                                            cat = it,
                                            onClick = {
                                                onCatClick(it)
                                            }
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }


                }
            }

        )
}

@Composable
private fun CatListItem(
    cat: CatData,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(197, 153, 150, 255)
        ),
        modifier = Modifier
            .padding(20.dp, 5.dp, 20.dp, 10.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        )
    ) {
        Column(
        ) {

            Image(
                painter = painterResource(R.drawable.cat),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Row {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)) {
                    Text(
                        text = cat.raceName,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = cat.details,
                        //maxLines = 1,
                        //overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                Box(modifier = Modifier,
                    contentAlignment = Alignment.BottomEnd
                    ) {
                    AppIconButton(modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.ArrowForward, onClick = {  })
                }
            }
        }
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


@Preview(showBackground = true)
@Composable
fun HelloCompPreview(){
    CatalistTheme {
        CatsListScreen(
            cats = SampleCats,
            onCatClick = {},
            onSearchClick = {},
        )
    }
}