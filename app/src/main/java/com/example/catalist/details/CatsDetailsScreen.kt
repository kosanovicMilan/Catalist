package com.example.catalist.details

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist.compose.AppIconButton
import com.example.catalist.compose.BreedImage
import com.example.catalist.errlod.ErrorScreen
import com.example.catalist.errlod.LoadingScreen
import com.example.catalist.uimodel.CatUIData

fun NavGraphBuilder.details(
    route : String,
    onBackClick: () -> Unit
) = composable(
    route = route
){navBackStackEnry ->
    val breedId = navBackStackEnry?.arguments?.getString("id")
        ?: throw IllegalStateException("cats id required")
    val breedDetailsViewModel = viewModel<CatsDetailsViewModel>(
        factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CatsDetailsViewModel(breedId = breedId) as T
            }
        }
    )

    val state = breedDetailsViewModel.state.collectAsState()

    Log.d("macaClick","u details: $breedId")
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
    if(state.loading && !state.error) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingScreen()
        }
    }else if(state.error){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ErrorScreen()
        }
    }else {
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


                    val breed = state.breed

                    if (breed != null) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = breed.name,
                                fontSize = 40.sp,
                                modifier = Modifier.padding(vertical = 15.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .height(240.dp)
                                    .fillMaxWidth()
                                    .background(
                                        Color(81, 80, 140, 255),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(8.dp)
                            ) {
                                BreedImage(
                                    url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
                                    contentDescription = "Cat Image",
                                    modifier = Modifier
                                        .height(250.dp)
                                        .fillMaxWidth()
                                )
                            }



                            Spacer(modifier = Modifier.padding(vertical = 5.dp))

                            Text(
                                text = breed.description,
                                fontSize = 20.sp
                            )
                            Divider(modifier = Modifier
                                .width(250.dp)
                                .padding(3.dp),
                                color = Color(81, 80, 140, 255))


//                            if(!breed.alt_names.equals("")){
//                                Text(text = "Alternative names: ${breed.alt_names}",
//                                    fontSize = 20.sp)
//                            }else{
//                                Text(text = "Alternative names: no alternative names",
//                                    fontSize = 20.sp)
//                            }

                            Divider(modifier = Modifier
                                .width(250.dp)
                                .padding(3.dp),
                                color = Color(81, 80, 140, 255))
                            Text(
                                text = "Life span: ${breed.life_span} years",
                                fontSize = 20.sp
                            )
                            Divider(modifier = Modifier
                                .width(250.dp)
                                .padding(3.dp),
                                color = Color(81, 80, 140, 255))
                            Text(
                                text = "Weight: ${breed.weight} pounds",
                                fontSize = 20.sp
                            )
                            Divider(modifier = Modifier
                                .width(250.dp)
                                .padding(3.dp),
                                color = Color(81, 80, 140, 255))
                            listOf(
                                "Grooming" to breed.grooming,
                                "Social needs" to breed.social_needs,
                                "Vocalisation" to breed.vocalisation,
                                "Energy level" to breed.energy_level,
                                "Stranger Friendly" to breed.stranger_friendly
                            ).forEach { (title, rating) ->
                                Text(
                                    text = "$title:",
                                    fontSize = 15.sp
                                )
                                Row {
                                    rating?.let {
                                        repeat(it, action = {
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                tint = Color.Yellow,
                                                contentDescription = "desc"
                                            )
                                        })
                                    }
                                }
                            }

                            breed.wikipedia_url?.let { FindMoreButton(url = it) }

                        }
                    }
                    else {
                        Text(text = "breed je null")
                    }
                }


        }

    )
}
}
@Composable
fun FindMoreButton(
     url : String
) {
    val context = LocalContext.current
    val openUrl = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            openUrl.launch(intent)
        },
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        Text("Find more")
    }
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
                name="Macedonian Short-hair",
                description = "wild cats that love to play",
                origin = "Macedonia",
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