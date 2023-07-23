package com.jakub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PostComponent(
    position: Int = 1,
    author: String = "",
    title: String = "",
    timestamp: String = "",
    label: String = "",
    imageUrl: String = "",
    onItemClick: (String) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(modifier = Modifier.padding(8.dp),
            onClick = { onItemClick(title) }) {

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(text = "#$position")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "icon"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Row {
                                Text(text = author)
                                Text(text = "  •  ")
                                Text(text = timestamp)
                            }
                            Text(text = title, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 6.dp)
                ) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.inversePrimary).padding(horizontal = 4.dp),
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                }

                Column(modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "post image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}