package com.github.ryu.andocchi.network

import com.github.ryu.andocchi.model.Github
import com.github.ryu.andocchi.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    suspend fun fetchLanguageStarRanking(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") page: String
    ) : Response<Github>
}
