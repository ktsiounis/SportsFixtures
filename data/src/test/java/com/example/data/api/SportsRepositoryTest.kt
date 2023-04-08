package com.example.data.api

import com.example.common.Result
import com.example.domain.models.Sport
import com.example.quotes.runTestWithDispatcher
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SportsRepositoryTest{

    private val clientMock = mockk<SportsApiClient>()
    private val repoUnderTest = SportsRepository(clientMock)

    private val errorMessage = "Not Found!"

}