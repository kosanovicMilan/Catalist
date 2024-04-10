package com.example.catalist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.catalist.ui.theme.CatalistTheme

@Composable
fun ErrorScreen() {
    Scaffold {
        paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(text = "ERORRR...")
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