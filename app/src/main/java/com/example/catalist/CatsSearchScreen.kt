package com.example.catalist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalist.ui.theme.CatalistTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsSearchScreen(

    onBackClick : () -> Unit,
    cats: List<CatData>
) {
    Scaffold(


        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Cat Searcher") },
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
        }
    ) {padding ->

        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
                        ) {


            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val searchQuery = remember { mutableStateOf("") }
                val focusManager = LocalFocusManager.current

                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    label = { Text("Search") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                    //.focusRequester(focusManager.requestFocus())
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Search")
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Column (
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                cats.forEach() {

                    if (it.raceName.contains("S")) {
                        Text(text = "nasao macu : ${it.raceName}")
                    }
                }
            }
        }


        }
    }




@Preview(showBackground = true)
@Composable
fun SearchPreview(){
    CatalistTheme {
        CatsSearchScreen(
            onBackClick = {},
            cats = SampleCats
        )
    }
}
