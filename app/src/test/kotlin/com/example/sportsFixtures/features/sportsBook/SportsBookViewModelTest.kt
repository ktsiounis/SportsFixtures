package com.example.sportsFixtures.features.sportsBook

import app.cash.turbine.test
import com.example.common.R
import com.example.domain.models.Event
import com.example.domain.models.Sport
import com.example.domain.usecases.SportsUseCase
import com.example.quotes.runTestWithDispatcher
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.Event.*
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test
import kotlin.random.Random

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
                    id = Random.nextLong().toString(),
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
                    id = Random.nextLong().toString(),
                    sportId = "FOOT",
                    name = "Panathinaikos - Olympiakos",
                    startTime = 1681795200,
                    isFavorite = true
                )
            )
        )
    )

    @Test
    fun `when event is marked as favorite, is marked also in cache`() = runTestWithDispatcher {
        vmUnderTest.state().test {
            coEvery { useCaseMock.setEventAsFavorite(any(), any()) } returns mockSportsEventFav

            vmUnderTest.onEvent(OnFavoriteButtonClicked(
                event = Event(
                    id = Random.nextLong().toString(),
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