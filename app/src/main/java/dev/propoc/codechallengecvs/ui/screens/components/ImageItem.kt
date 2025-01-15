package dev.propoc.codechallengecvs.ui.screens.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import dev.propoc.codechallengecvs.model.Item

@Composable
fun ImageItem(item: Item, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val itemJson = Gson().toJson(item)
                navController.navigate("detail/${Uri.encode(itemJson)}")
            }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
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
        }
    }
}



