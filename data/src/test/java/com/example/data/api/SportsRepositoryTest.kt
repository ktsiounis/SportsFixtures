package com.example.data.api

import app.cash.turbine.test
import com.example.common.Result
import com.example.domain.models.Event
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

    private val sportRawMock = SportRaw(
        id = "FOOT",
        name = "SOCCER",
        events = listOf(
            EventRaw(
                id = "123455",
                sportId = "FOOT",
                name = "Barcelona - Real Madrid",
                startTime = 1111122222
            )
        )
    )
    private val sportsRawMock = listOf(sportRawMock)

    private val eventDomainMock = Event(
        id = "123455",
        sportId = "FOOT",
        name = "Barcelona - Real Madrid",
        startTime = 1111122222,
        isFavorite = false
    )
    private val sportDomainMock = Sport(
        id = "FOOT",
        name = "SOCCER",
        icon = 2131165286,
        events = listOf(
            eventDomainMock
        )
    )
    private val sportDomainWithFavEventMock = Sport(
        id = "FOOT",
        name = "SOCCER",
        icon = 2131165286,
        events = listOf(
            eventDomainMock.copy(isFavorite = true)
        )
    )
    private val sportsDomainMock = listOf(sportDomainMock)

    private val errorMsg = "Something went wrong"

    @Test
    fun `when sports fetching is done successfully, a success result with the list of sports is returned`() =
        runTestWithDispatcher {
            coEvery { clientMock.getSports() } returns Result.Success(sportsRawMock)

            repoUnderTest.fetchSports().test() {
                awaitItem() shouldBe Result.Success(sportsDomainMock)
                awaitComplete()
                expectNoEvents()
            }
        }

    @Test
    fun `when sports fetching is failed, an error result with a message is returned`() =
        runTestWithDispatcher {
            coEvery { clientMock.getSports() } returns Result.Error(errorMsg)

            repoUnderTest.fetchSports().test() {
                awaitItem() shouldBe Result.Error(errorMsg)
                awaitComplete()
                expectNoEvents()
            }
        }

    @Test
    fun `when user sets an event as favorite, the sports list is also update with event changed`() =
        runTestWithDispatcher {
            repoUnderTest.sports.test {
                coEvery { clientMock.getSports() } returns Result.Success(sportsRawMock)
                repoUnderTest.fetchSports()

                repoUnderTest.setEventAsFavorite(event = eventDomainMock, isFavorite = true)

                skipItems(2)
                awaitItem() shouldBe listOf(sportDomainWithFavEventMock)
                expectNoEvents()
            }
        }

}