package com.github.ryu.andocchi.repository

import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.network.RoadMapService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val service: RoadMapService) {
    private var cache: List<Path>? = null

    // キャッシュを使って、データを取得するのかリモートから取得するのかをRepositoryの判断に任せる
    suspend fun fetchRoadMap() = service.fetchRoadMap().body().also {
        cache = it
    }
    suspend fun getRoadMap() = cache ?: fetchRoadMap()
}
