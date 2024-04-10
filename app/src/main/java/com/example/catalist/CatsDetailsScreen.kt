package com.example.catalist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalist.ui.theme.CatalistTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailsScreen(
    cat : CatData,
    onBackClick : () -> Unit
){
    Scaffold (

        topBar = {
            Column(

            ) {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Cat Details") },
                        navigationIcon =
                                {
                                    AppIconButton(modifier=Modifier.size(
                                        width = 15.dp,
                                        height = 15.dp
                                    ),imageVector = Icons.Default.ArrowBack
                                    , onClick = onBackClick)
                                }
                    )
                Divider()

            }
        },


        content = {
                paddingValues ->

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.LightGray),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Spacer(modifier = Modifier.padding(vertical = 15.dp))

                Text(
                    text = cat.raceName,
                    fontSize = 35.sp
                    )

                Spacer(modifier = Modifier.padding(vertical = 15.dp))

                Image(
//                        modifier = Modifier.size(
//                            width = 25.dp,
//                            height = 25.dp,
//                        ),
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = "maca",

                )
                Text(
                    text = "Average life span of these breeads is:",
                    fontSize = 20.sp
                )
                
                Text(
                    text = cat.lifeSpan,
                )

                Text(
                    text = cat.details,
                )

                Text(
                    text = "This is some of the traits of this breed",
                )
                for (s in cat.traitList) {
                    AssistChip(
                        onClick = { },
                        label = { Text(s) },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Localized description",
                                Modifier.size(AssistChipDefaults.IconSize)
                            )
                        }
                    )
                }

            }
        }

    )
}


@Preview(showBackground = true)
@Composable
fun CatDetailsScreenPreview(){
    CatalistTheme {
        CatDetailsScreen(cat = CatData(id = "1",
            raceName = "Mily Ramondy",
            lifeSpan = "8 - 12",
            details = "This is the most beautyfull breed on market they have nice hair, they are healthy",
            imagePath = "cat.png",
             traitList = listOf(
                 "umiljata",
                 "dobra",
                 "igrase"
             )
        )
            ,
            onBackClick = {}
        )

    }
}