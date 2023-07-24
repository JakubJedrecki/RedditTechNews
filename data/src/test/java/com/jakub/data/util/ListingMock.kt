package com.jakub.data.util

import com.jakub.data.dto.Children
import com.jakub.data.dto.Data
import com.jakub.data.dto.Listing
import com.jakub.data.dto.MainData
import com.jakub.domain.models.Post
import com.jakub.domain.shared.ResultResponse

val listing = Listing(
    kind = "",
    data = MainData(
        children = arrayListOf(
            Children(
                kind = "",
                Data(
                    title = "title 1",
                    linkFlairText = "label1",
                    thumbnail = "",
                    created = 123,
                    id = "1",
                    author = "author1",
                    createdUtc = 123
                )
            ),
            Children(
                kind = "",
                Data(
                    title = "title 2",
                    linkFlairText = "label2",
                    thumbnail = "",
                    created = 123,
                    id = "2",
                    author = "author2",
                    createdUtc = 123
                )
            ),
            Children(
                kind = "",
                Data(
                    title = "title 3",
                    linkFlairText = "label3",
                    thumbnail = "",
                    created = 123,
                    id = "3",
                    author = "author3",
                    createdUtc = 123
                )
            )
        )
    )
)

val expectedListing = ResultResponse.Success(
    listOf(
        Post(
            author = "author1",
            timestamp = "123",
            title = "title 1",
            imageUrl = "",
            linkFlairText = "label1"
        ),
        Post(
            author = "author2",
            timestamp = "123",
            title = "title 2",
            imageUrl = "",
            linkFlairText = "label2"
        ),
        Post(
            author = "author3",
            timestamp = "123",
            title = "title 3",
            imageUrl = "",
            linkFlairText = "label3"
        ),
    )
)