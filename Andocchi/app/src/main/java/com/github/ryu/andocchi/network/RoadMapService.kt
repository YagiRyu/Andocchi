package com.github.ryu.andocchi.network

import com.github.ryu.andocchi.model.Path
import retrofit2.Response
import retrofit2.http.GET

interface RoadMapService {
    @GET("paths")
    suspend fun fetchRoadMap(): Response<List<Path>>
}
