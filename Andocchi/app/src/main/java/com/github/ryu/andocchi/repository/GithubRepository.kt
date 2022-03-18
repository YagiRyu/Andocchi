package com.github.ryu.andocchi.repository

import com.github.ryu.andocchi.model.Github
import com.github.ryu.andocchi.network.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val service: GithubService){
    private var cache: Flow<Response<Github>>? = null

    private suspend fun fetchStarRanking(language: String,
                                 sort: String,
                                 page: String
    ): Flow<Response<Github>> = flow {
        emit(service.fetchLanguageStarRanking(language, sort, page))
    }.flowOn(Dispatchers.IO)

    suspend fun getStarRanking(language: String,
                               sort: String,
                               page: String
    ) = cache ?: fetchStarRanking(language, sort, page)
}
