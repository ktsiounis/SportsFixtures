package com.example.data.api

import com.example.network.ApiProvider
import com.example.network.utils.toResult

class SportsApiClient(
    private val apiProvider: ApiProvider<SportsApi>
) {

    suspend fun getSports() = apiProvider.api().getSports().toResult()

}