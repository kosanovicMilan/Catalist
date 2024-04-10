package com.example.catalist


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "cats"
    ) {

        composable(
            route = "cats"
        ){
            CatsListScreen(
                cats = CatsRepository.allCats(),
                onCatClick = { cat ->
                    navController.navigate(
                        route = "cats/${cat.id}"
                    )
                },
                onSearchClick = {
                    navController.navigate("cats/search")
                }
            )
        }

        composable(
            route = "cats/{catId}"
        ){ backStackEntry ->
            val catDataId = backStackEntry.getCatDataOrThrow()
            val data = remember(catDataId) {
                CatsRepository.getCatById(
                    id = catDataId
                )
            }



            if (data != null) {
                CatDetailsScreen(
                    cat = data,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
            else
                ErrorScreen()
        }

        composable(
            route = "cats/search"
        ){
            CatsSearchScreen(
                onBackClick = {
                    navController.navigateUp()
                              },
                cats = CatsRepository.allCats()
            )
        }
    }
}

private fun NavBackStackEntry.getCatDataOrThrow(): String {
    return arguments?.getString("catId") ?: throw IllegalStateException("Cat id is required...")
}
