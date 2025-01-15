package dev.propoc.codechallengecvs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import dev.propoc.codechallengecvs.model.Item
import dev.propoc.codechallengecvs.ui.screens.DetailScreen
import dev.propoc.codechallengecvs.ui.screens.SearchScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(navController = navController)
        }
        composable(
            route = DETAIL_ROUTE,
            arguments = listOf(navArgument("item") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val itemJson = backStackEntry.arguments?.getString("item")
            val item = Gson().fromJson(itemJson, Item::class.java)

            DetailScreen(navController = navController, item = item)
        }
    }
}

const val DETAIL_ROUTE = "detail/{item}"