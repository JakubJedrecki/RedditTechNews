package com.jakub.data.repositories.interfaces

import com.jakub.domain.models.Post
import com.jakub.domain.shared.ResultResponse

interface TechNewsRepository {
    suspend fun getTechNews(): ResultResponse<List<Post>>
}