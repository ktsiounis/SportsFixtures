package com.example.sportsFixtures.features.sportsBook

import app.cash.turbine.test
import com.example.common.R
import com.example.common.Result
import com.example.domain.models.Event
import com.example.domain.models.Sport
import com.example.domain.usecases.SportsUseCase
import com.example.quotes.runTestWithDispatcher
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.Event.*
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SportsBookViewModelTest{

    private val useCaseMock = mockk<SportsUseCase>()
    private val vmUnderTest = SportsBookViewModel(useCaseMock)

    private val mockSports = listOf(
        Sport(
            name = "FOOTBALL",
            id = "FOOT",
            icon = R.drawable.soccer_24,
            events = listOf(
                Event(
                    id = "12345",
                    sportId = "FOOT",
                    name = "Panathinaikos - Olympiakos",
                    startTime = 1681795200,
                    isFavorite = false
                )
            )
        )
    )

    private val mockSportsEventFav = listOf(
        Sport(
            name = "FOOTBALL",
            id = "FOOT",
            icon = R.drawable.soccer_24,
            events = listOf(
                Event(
                    id = "12345",
                    sportId = "FOOT",
                    name = "Panathinaikos - Olympiakos",
                    startTime = 1681795200,
                    isFavorite = true
                )
            )
        )
    )

    @Test
    fun `when screen in initialized, sports are fetched and presented on the screen`() =
        runTestWithDispatcher {
            vmUnderTest.state().test {
                coEvery { useCaseMock.fetchSports() } returns flowOf(Result.Success(mockSports))
                coEvery { useCaseMock.sports } returns MutableStateFlow(mockSports)
                coEvery { useCaseMock.setEventAsFavorite(any(), any()) } returns Unit

                vmUnderTest.onEvent(OnScreenInitialized)

                skipItems(1)
                awaitItem() shouldBe SportsBookScreenContract.State(sportsWithEvents = mockSports)
                expectNoEvents()
            }
        }

    @Test
    fun `when event is marked as favorite, is marked also in cache and the list is updated`() =
        runTestWithDispatcher {
            vmUnderTest.state().test {
                coEvery { useCaseMock.fetchSports() } returns flowOf(Result.Success(mockSports))
                coEvery { useCaseMock.sports } returns MutableStateFlow(mockSportsEventFav)
                coEvery { useCaseMock.setEventAsFavorite(any(), any()) } returns Unit

                vmUnderTest.onEvent(OnScreenInitialized)

                vmUnderTest.onEvent(OnFavoriteButtonClicked(
                    event = Event(
                        id = "12345",
                        sportId = "FOOT",
                        name = "Panathinaikos - Olympiakos",
                        startTime = 1681795200,
                        isFavorite = false
                    ),
                    isFavorite = true
                ))

                skipItems(1)
                awaitItem() shouldBe SportsBookScreenContract.State(sportsWithEvents = mockSportsEventFav)
                expectNoEvents()
            }
        }

}