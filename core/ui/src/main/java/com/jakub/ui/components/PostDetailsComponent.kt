package com.jakub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Preview
@Composable
fun PostDetailsComponent(
    author: String = "author 1",
    title: String = "title 1",
    timestamp: String = "12345678",
    label: String = "some label",
    imageUrl: String = "url",
    upVotes: Int = 0,
    downVotes: Int = 0,
    numComments: Int = 0
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
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
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.inversePrimary)
                        .padding(horizontal = 4.dp),
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }

            Column(modifier = Modifier
                .padding(8.dp)
                .wrapContentHeight()
                .fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "post image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(color = MaterialTheme.colorScheme.inversePrimary)
                        .padding(4.dp)
                ) {
                    Text(text = "Ups: ")
                    Text(text = upVotes.toString())
                }
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(color = MaterialTheme.colorScheme.inversePrimary)
                        .padding(4.dp)
                ) {
                    Text(text = "Downs: ")
                    Text(text = downVotes.toString())
                }
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(color = MaterialTheme.colorScheme.inversePrimary)
                        .padding(4.dp)
                ) {
                    Text(text = "Comments: ")
                    Text(text = numComments.toString())
                }
            }
        }
    }
}