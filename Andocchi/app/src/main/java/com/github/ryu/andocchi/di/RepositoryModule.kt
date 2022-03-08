package com.github.ryu.andocchi.di

import com.github.ryu.andocchi.network.RoadMapService
import com.github.ryu.andocchi.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeRepository(service: RoadMapService) = HomeRepository(service)
}
