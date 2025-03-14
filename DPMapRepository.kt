package com.example.dpmap.data

import com.example.dpmap.network.DPMap
//import com.example.dpmap.network.DPApi
import com.example.dpmap.network.DPApiService

interface DPMapRepository {
    suspend fun getDPMap(): List<DPMap>
}

class NetworkDPMapRepository(
    private val dpApiService: DPApiService
) : DPMapRepository{
    override suspend fun getDPMap(): List<DPMap> = dpApiService.getMap()
}