package com.example.domain.usecases

import com.example.domain.contracts.SportsRepositoryContract

class SportsUseCase(
    private val restRepository: SportsRepositoryContract,
) {

    suspend fun getSports() = restRepository.getSports()

}