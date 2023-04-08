package com.example.domain.contracts

import com.example.domain.models.Sport
import kotlinx.coroutines.flow.Flow
import com.example.common.Result

interface SportsRepositoryContract {

    suspend fun getSports(): Flow<Result<List<Sport>>>

}