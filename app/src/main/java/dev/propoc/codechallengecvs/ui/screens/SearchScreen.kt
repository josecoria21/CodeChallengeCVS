package dev.propoc.codechallengecvs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.propoc.codechallengecvs.ui.screens.components.ImageItem
import dev.propoc.codechallengecvs.ui.screens.components.SearchBar
import dev.propoc.codechallengecvs.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SearchBar(
            query = query,
            onQueryChanged = { newQuery ->
                viewModel.onQueryChanged(newQuery)
            },
            onSearch = { viewModel.fetchData(query) }
        )

        when {
            state.isLoading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            state.errorMessage != null -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(state.errorMessage!!)
            }
            else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.images.size) { item ->
                    ImageItem(item = state.images[item], navController = navController)
                }
            }
        }
    }
}
