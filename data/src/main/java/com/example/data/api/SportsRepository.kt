package com.example.data.api

import com.example.domain.contracts.SportsRepositoryContract
import com.example.domain.models.Sport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.example.common.Result

class SportsRepository(
    private val client: SportsApiClient
) : SportsRepositoryContract {

    override suspend fun getSports(): Flow<Result<List<Sport>>> = flowOf(
        client.getSports().toSportsResult()
    )

}