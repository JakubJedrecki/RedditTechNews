package com.jakub.data.mappers

import com.jakub.data.dto.Data
import com.jakub.data.dto.Listing
import com.jakub.domain.models.Post

fun Listing.mapToDomain(): List<Post> {
    val news = mutableListOf<Post>()
    this.data.children.map { children ->
        children.data.mapToDomain()
    }.let { news.addAll(it) }

    return news
}

fun Data.mapToDomain(): Post = Post(
    author = author ?: "",
    timestamp = created.toString(),
    title = title ?: "",
    imageUrl = thumbnail ?: "",
    linkFlairText = linkFlairText ?: ""
)