package com.example.catalist.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catalist.details.CatDetailsScreen
import com.example.catalist.details.details
import com.example.catalist.junk.CatsRepository
import com.example.catalist.search.CatsSearchScreen
import com.example.catalist.errlod.ErrorScreen
import com.example.catalist.list.breeds

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "cats"
    ) {

        breeds(
            route = "cats",
            onCatClick = {
                navController.navigate(route = "cats/$it")
            },
            onSearchClik = {
                navController.navigate(route = "cats/search")
            }
        )

        details(
            route = "cats/{catId}",
            onBackClick = {
                navController.navigateUp()
            }
        )





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
