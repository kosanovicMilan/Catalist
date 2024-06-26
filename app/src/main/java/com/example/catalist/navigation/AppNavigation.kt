package com.example.catalist.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catalist.details.details
import com.example.catalist.junk.CatsRepository
import com.example.catalist.search.CatsSearchScreen
import com.example.catalist.list.breeds
import com.example.catalist.search.search

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
                Log.d("macaClick","u nav klinko na macu: $it")
                navController.navigate(route = "cats/$it")
            },
            onSearchClik = {
                navController.navigate(route = "cats/search")
            }
        )

        details(
            route = "cats/{id}",
            onBackClick = {
                navController.navigateUp()
            }
        )





        search(
            route = "cats/search",
            onCatClick = {
                navController.navigate("cats/$it")
            },
            onBackClick = { navController.navigateUp() }
        )
    }
}

private fun NavBackStackEntry.getCatDataOrThrow(): String {
    return arguments?.getString("catId") ?: throw IllegalStateException("Cat id is required...")
}
