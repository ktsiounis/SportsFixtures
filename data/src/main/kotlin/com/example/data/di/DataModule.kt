package com.example.data.di

import com.example.data.api.SportsApi
import com.example.data.api.SportsApiClient
import com.example.data.api.SportsRepository
import com.example.domain.contracts.SportsRepositoryContract
import com.example.network.ApiProvider
import com.google.gson.Gson
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    factory { SportsApiClient(apiProvider = ApiProvider(SportsApi::class.java)) }
    factoryOf(::SportsRepository) bind SportsRepositoryContract::class
    single { Gson() }

}