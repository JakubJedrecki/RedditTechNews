package com.jakub.data.repositories

import com.jakub.data.mappers.mapToDomain
import com.jakub.data.repositories.interfaces.TechNewsRepository
import com.jakub.data.services.HomeService
import com.jakub.domain.models.Post
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import javax.inject.Inject

class TechNewsRepositoryImpl @Inject constructor(
    private val homeService: HomeService
): TechNewsRepository {

    override suspend fun getTechNews(): ResultResponse<List<Post>> {
        return try {
            homeService.getLatestTechnologyNews().run {
                this.body()?.let {
                    if (isSuccessful) {
                        ResultResponse.Success(it.mapToDomain())
                    } else {
                        val error = this.errorBody()?.toString() ?: ""
                        ResultResponse.Error(ErrorEntity.Generic(error))
                    }
                } ?: ResultResponse.Error(ErrorEntity.Unknown())
            }
        } catch (exception: Exception) {
            ResultResponse.Error(ErrorEntity.Unknown(exception.message ?: ""))
        }
    }
}