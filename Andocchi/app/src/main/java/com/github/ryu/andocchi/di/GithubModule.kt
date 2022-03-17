package com.github.ryu.andocchi.di

import com.github.ryu.andocchi.network.GithubService
import com.github.ryu.andocchi.utils.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubModule {

    // GitHub
    @Singleton
    @Provides
    @Named("Github")
    fun provideGithubRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.GITHUB_API)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Singleton
    @Provides
    fun provideGithubService(@Named("Github") retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)
}