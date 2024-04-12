package com.example.catalist.errlod

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalist.R
import com.example.catalist.compose.RotatingImage
import com.example.catalist.ui.theme.CatalistTheme

@Composable
fun ErrorScreen() {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(20.dp, 10.dp, 6.dp, 10.dp),
                text = "Ooops, looks like we run into an error :( ",
                fontSize = 25.sp
            )
            // Prikazujemo sliku sa animacijom rotacije
            RotatingImage(
                painter = painterResource(R.drawable.milyramondy),
                contentDescription = null,
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview(){
    CatalistTheme {
        ErrorScreen()
    }
}