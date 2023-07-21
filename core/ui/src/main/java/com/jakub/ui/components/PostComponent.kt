package com.jakub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakub.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PostComponent(
    position: Int,
    author: String = "",
    title: String = "",
    timestamp: String = "",
    label: String = "",
    imageUrl: String = "",
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(modifier = Modifier.padding(8.dp),
            onClick = { /* TODO */ }) {

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(text = "#$position")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "icon"
                        ) //TODO replace with coil
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = title, fontWeight = FontWeight.Bold)
                            Row {
                                Text(text = author)
                                Text(text = "  •  ")
                                Text(text = timestamp)
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(color = Color.DarkGray)
                            .wrapContentSize()
                            .padding(2.dp)
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.LightGray
                        )
                    }
                }
                Column(modifier = Modifier.padding(8.dp)) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.aaaa),
                        contentDescription = ""
                    ) //TODO replace with coil
                }
            }
        }
    }
}