package com.github.ryu.andocchi.di

import com.github.ryu.andocchi.data.UserDao
import com.github.ryu.andocchi.network.GithubService
import com.github.ryu.andocchi.network.RoadMapService
import com.github.ryu.andocchi.repository.GithubRepository
import com.github.ryu.andocchi.repository.HomeRepository
import com.github.ryu.andocchi.repository.UserRepository
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

    @Singleton
    @Provides
    fun provideUserRepository(dao: UserDao) = UserRepository(dao)

//    @Singleton
//    @Provides
//    fun provideGithubRepository(service: GithubService) = GithubRepository(service)

}
