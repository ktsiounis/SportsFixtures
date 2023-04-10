package com.example.sportsFixtures.sportsBook

import com.example.common.resolveSportIconFromId
import com.example.domain.models.Event
import com.example.domain.models.Sport
import kotlin.random.Random

private val sportIds = listOf(
    "FOOT",
    "BASK",
    "TENN",
    "TABL",
    "ESPS",
    "HAND",
    "BCHV",
)

private val sportNames = listOf(
    "FOOTBALL",
    "BASKETBALL",
    "TENNIS",
    "TABLE TENNIS",
    "E-SPORTS",
    "HANDBALL",
    "BEACH VOLLEYBALL",
)

private var currentSportId = ""
internal val sports = MutableList(7) {
    Sport(
        id = sportIds[it].also { id -> currentSportId = id },
        name = sportNames.random(),
        icon = currentSportId.resolveSportIconFromId(),
        events = MutableList(Random.nextInt(10)) {
            Event(
                id = Random.nextLong().toString(),
                sportId = currentSportId,
                name = "Team ${Random.nextInt()} - Team ${Random.nextInt()}",
                isFavorite = false,
                startTime = Random.nextLong(1681795200, 2681795800)
            )
        }
    )
}