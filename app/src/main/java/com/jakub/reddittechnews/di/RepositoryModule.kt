package com.jakub.reddittechnews.di

import com.jakub.data.repositories.TechNewsRepositoryImpl
import com.jakub.domain.repositories.TechNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTechNewsRepository(techNewsRepositoryImpl: TechNewsRepositoryImpl): TechNewsRepository
}