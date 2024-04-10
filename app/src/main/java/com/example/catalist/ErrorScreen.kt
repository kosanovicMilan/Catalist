package com.example.catalist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalist.ui.theme.CatalistTheme

@Composable
fun ErrorScreen() {
    Scaffold(

    ) {
        paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier.padding(20.dp,10.dp,6.dp,10.dp),
                text = "Ooops, looks like you have run into the problem...",
                fontSize = 25.sp)

            Image(
                painter = painterResource(R.drawable.cat),
                contentDescription = null, // decorative
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )



        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview(){
    CatalistTheme {
        ErrorScreen()
    }
}