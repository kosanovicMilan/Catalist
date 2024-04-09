package com.example.catalist

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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.catalist.ui.theme.CatalistTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloComp(
    cats : List<CatData>,
    onCatClick: (CatData) -> Unit
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
                                        onClick = {}) {

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
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Spacer(modifier = Modifier.padding(vertical = 15.dp))

                   cats.forEach{
                       Column {
                           key(it.id){
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { onClick() }
    ) {

        Column() {
            Text(
                modifier = Modifier.padding(all = 16.dp)
                ,text = cat.raceName

            )

            Row{
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .weight(weight = 1f),
                    text = cat.details
                )

                AppIconButton(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    onClick = {}
                )
            }

        }

    }
}


@Composable
fun AppIconButton(

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
        HelloComp(
            cats = SampleCats,
            onCatClick = {},
        )
    }
}