package com.github.ryu.andocchi.repository

import com.github.ryu.andocchi.network.RoadMapService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val service: RoadMapService) {
    suspend fun fetchRoadMap() = service.fetchRoadMap().body()
}
