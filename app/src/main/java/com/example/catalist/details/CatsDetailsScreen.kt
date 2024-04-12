package com.example.catalist.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist.compose.AppIconButton
import com.example.catalist.R
import com.example.catalist.compose.BreedImage
import com.example.catalist.errlod.ErrorScreen
import com.example.catalist.list.model.CatUIData

fun NavGraphBuilder.details(
    route : String,
    onBackClick: () -> Unit
) = composable(
    route = route
){
    val breedDetailsViewModel = viewModel<CatsDetailsViewModel>()
    val state = breedDetailsViewModel.state.collectAsState()

    CatDetailsScreen(
        state = state.value,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailsScreen(
    state : BreedDetailsContract.BreedDetailsState,
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
                    .background(Color(139, 179, 175, 255)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                if(state.loading && !state.error) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorScreen()
                    }
                }else if(state.error){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "ERORRRR")
                    }
                }else {
                    val breed = state.breed

                    if(breed != null) {

                        Text(
                            text = breed.name,
                            fontSize = 40.sp, // Prilagodite veličinu teksta prema potrebi
                            modifier = Modifier.padding(vertical = 15.dp) // Prilagodite razmak oko teksta prema potrebi
                        )

                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                                .background(Color.Yellow, shape = RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            BreedImage(
                                url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
                                contentDescription = "Cat Image",
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                            )
                        }



                        Spacer(modifier = Modifier.padding(vertical = 15.dp))

                        Text(
                            text = "Average life span of these breeads is:",
                            fontSize = 20.sp
                        )

                        Text(
                            text = breed.life_span,
                        )

                        Text(
                            text = breed.description,
                        )


                    }
                    else {
                        Text(text = "breed je null")
                    }
                }

            }
        }

    )
}
class PreviewBreed : PreviewParameterProvider<BreedDetailsContract.BreedDetailsState> {
    override val values: Sequence<BreedDetailsContract.BreedDetailsState> = sequenceOf(

        BreedDetailsContract.BreedDetailsState(
            breedId = "maca",
            loading = true
        ),
        BreedDetailsContract.BreedDetailsState(
            breedId = "maca",
            loading = false ,
            error = true
        ),
        BreedDetailsContract.BreedDetailsState(
            breedId = "maca",
            breed = CatUIData(
                id="maca",
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
                vocalisation = 4
            ),
            loading = false,
            error = false
        )

    )
}
@Preview
@Composable
private fun PreviewBreedCat(
    @PreviewParameter(PreviewBreed :: class) breedDetailsState : BreedDetailsContract.BreedDetailsState,
){
    CatDetailsScreen(
        state = breedDetailsState
        , onBackClick = {}
    )
}

//
//@Preview(showBackground = true)
//@Composable
//fun CatDetailsScreenPreview(){
//    CatalistTheme {
//        CatDetailsScreen(cat = CatData(id = "1",
//            raceName = "Mily Ramondy",
//            lifeSpan = "8 - 12",
//            details = "This is the most beautyfull breed on market they have nice hair, they are healthy",
//            imagePath = "cat.png",
//             traitList = listOf(
//                 "umiljata",
//                 "dobra",
//                 "igrase"
//             )
//        )
//            ,
//            onBackClick = {}
//        )
//
//    }
//}