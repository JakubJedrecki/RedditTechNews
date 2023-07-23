package com.jakub.data.dto

import com.google.gson.annotations.SerializedName

data class Listing(
    @SerializedName("kind") var kind: String = "",
    @SerializedName("data") var data: MainData = MainData()
)

data class MainData(
    @SerializedName("children") var children: ArrayList<Children> = arrayListOf()
)

data class Children(
    @SerializedName("kind") var kind: String = "",
    @SerializedName("data") var data: Data = Data()

)

data class Data(
    @SerializedName("title") var title: String? = null,
    @SerializedName("link_flair_text") var linkFlairText: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("created") var created: Int? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("created_utc") var createdUtc: Int? = null,
)

data class Images(
    @SerializedName("resolutions") var resolutions: ArrayList<Resolutions> = arrayListOf(),
    @SerializedName("id") var id: String? = null
)

data class Resolutions(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)