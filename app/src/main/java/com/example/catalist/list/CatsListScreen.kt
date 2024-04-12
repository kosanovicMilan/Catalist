package com.example.catalist.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons

import androidx.compose.runtime.key

import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist.compose.CatListItem
import com.example.catalist.errlod.ErrorScreen
import com.example.catalist.uimodel.CatUIData
import com.example.catalist.errlod.LoadingScreen


fun NavGraphBuilder.breeds(
    route : String,
    onCatClick: (String) -> Unit,
    onSearchClik: () -> Unit
) = composable(
    route = route
){
    val breedListViewModel = viewModel<BreedListViewModel>()

    val state = breedListViewModel.state.collectAsState()


    CatsListScreen(
        state = state.value,
        onCatClick = onCatClick,
        onSearchClick = onSearchClik
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsListScreen(
    state : BreedListContract.BreedListState,
    onCatClick: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    if(state.loading && !state.error){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            LoadingScreen()
        }
    }else if(state.error){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            ErrorScreen()
        }
    }
    else {
        Scaffold(

            topBar = {
                Column(

                ) {
                    CenterAlignedTopAppBar(title = { Text(text = "CatsList") })
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
            content = { paddingValues ->

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    val list = state.breeds

                    list.forEach {

                        key(it.id) {
                            CatListItem(
                                cat = it,
                                onClick = {
                                    onCatClick(it.id)
                                    Log.d("macaClick", "klikno iz liste macu:  ${it.id}")
                                }
                            )
                        }
                    }

                }


            }


        )
    }
}


class PreviewList : PreviewParameterProvider<BreedListContract.BreedListState>{
    override val values: Sequence<BreedListContract.BreedListState> = sequenceOf(

        BreedListContract.BreedListState(
            loading = true,
        ),

        BreedListContract.BreedListState(
            loading = false,
            breeds = listOf(



                CatUIData(
                    id="cat1",
                    name="Pesian",
                    description = "wild cats that love to play",
                    origin = "SRB",
                    life_span = "15 - 20",
                    temperament = "funny,strong,temper,angty",
                    weight = "12-20",
                    grooming = 5,
                    energy_level = 4,
                    hypoallergenic = 1,
                    intelligence = 2,
                    stranger_friendly = 3,
                    vocalisation = 4,
                    social_needs = 4,
                    wikipedia_url = "nesto",
                    image_url = "nesto"
                        ),
                CatUIData(
                    id="cat2",
                    name="British",
                    description = "wild cats that love to play",
                    origin = "SRB",
                    life_span = "15 - 20",
                    temperament = "silly,stynky,fearless,something",
                    weight = "12-20",
                    grooming = 5,
                    energy_level = 4,
                    hypoallergenic = 1,
                    intelligence = 2,
                    stranger_friendly = 3,
                    vocalisation = 4,
                    social_needs = 4,

                    wikipedia_url = "nesto",
                    image_url = "nesto"
                ),
                CatUIData(
                    id="cat3",
                    name="Scotish",
                    description = "wild cats that love to play",
                    origin = "Serbia",
                    life_span = "15 - 20",
                    temperament = "funny,strong,temper,angty",
                    weight = "12-20",
                    grooming = 5,
                    energy_level = 4,
                    hypoallergenic = 1,
                    intelligence = 2,
                    stranger_friendly = 3,
                    vocalisation = 4,
                    social_needs = 4,

                    wikipedia_url = "nesto",
                    image_url = "nesto"
                )
            )

        )
    )
}





@Preview
@Composable
private fun PreviewBreedList(
    @PreviewParameter(PreviewList :: class) breedListState: BreedListContract.BreedListState,
){
    CatsListScreen(
        state = breedListState,
        onCatClick = {},
        onSearchClick = {}
    )
}


