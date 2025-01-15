package dev.propoc.codechallengecvs.ui.screens

import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.propoc.codechallengecvs.model.Item
import dev.propoc.codechallengecvs.viewmodel.DetailViewModel

@Composable
fun DetailScreen(navController: NavController, item: Item, viewModel: DetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        val dateString = item.published
        viewModel.convertDate(dateString)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = item.media.m,
            contentDescription = "Item Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = item.tags.replaceFirstChar { it.uppercase() })

        if (uiState.convertedDate != null) {
            Text(
                text = "Published: ${uiState.convertedDate}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (uiState.errorMessage != null) {
            Text(
                text = "Error: ${uiState.errorMessage}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red
            )
        }
    }
}
