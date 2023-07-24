package com.jakub.data.services

import com.jakub.data.dto.Listing
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    //get latest news
    @GET("/r/technology/new.json")
    suspend fun getLatestTechnologyNews(): Response<Listing>
}