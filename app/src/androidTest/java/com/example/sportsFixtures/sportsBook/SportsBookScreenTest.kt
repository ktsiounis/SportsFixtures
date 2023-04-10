package com.example.sportsFixtures.sportsBook

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.common.TestTag
import com.example.sportsFixtures.features.sportsBook.SportsBookContent
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract
import org.junit.Rule
import org.junit.Test

internal class SportsBookScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenSportsListIsEmpty_thenEmptyStateWithInfoIsShown() {
        composeTestRule.setContent {
            Box {
                SportsBookContent(
                    modifier = Modifier,
                    uiState = SportsBookScreenContract.State(),
                    onEvent = {}
                )
            }
        }

        composeTestRule
            .onNodeWithTag(TestTag.SportsBookScreen.EMPTY_STATE)
            .assertIsDisplayed()
    }

    @Test
    fun whenSportsListIsNotEmpty_thenSportsWithEventsAreDisplayed() {
        composeTestRule.setContent {
            Box {
                SportsBookContent(
                    modifier = Modifier,
                    uiState = SportsBookScreenContract.State(sportsWithEvents = sports),
                    onEvent = {}
                )
            }
        }

        composeTestRule
            .onAllNodesWithTag(TestTag.SportsBookScreen.SPORT_SECTION)
            .assertAll(isEnabled())
    }

    @Test
    fun givenSportsWithEventsAreDisplayed_whenASportTitleIsClicked_thenEventsAreCollapsed() {
        composeTestRule.setContent {
            Box {
                SportsBookContent(
                    modifier = Modifier,
                    uiState = SportsBookScreenContract.State(sportsWithEvents = sports),
                    onEvent = {}
                )
            }
        }

        composeTestRule
            .onNodeWithTag(TestTag.SportsBookScreen.SPORT_SECTION_TITLE + " - FOOT")
            .performClick()

        composeTestRule
            .onNodeWithTag(TestTag.SportsBookScreen.EVENTS_ROW + " - FOOT")
            .assertDoesNotExist()
    }

    @Test
    fun givenSportEventsAreCollapsed_whenASportTitleIsClicked_thenEventsExpanded() {
        composeTestRule.setContent {
            Box {
                SportsBookContent(
                    modifier = Modifier,
                    uiState = SportsBookScreenContract.State(sportsWithEvents = sports),
                    onEvent = {}
                )
            }
        }

        composeTestRule
            .onNodeWithTag(TestTag.SportsBookScreen.SPORT_SECTION_TITLE + " - FOOT")
            .performClick()
            .performClick()

        composeTestRule
            .onNodeWithTag(TestTag.SportsBookScreen.EVENTS_ROW + " - FOOT")
            .assertExists()
    }

}