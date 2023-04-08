package com.example.sportsFixtures.features.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.Green70
import com.example.designsystem.Theme.spacing
import com.example.designsystem.Typography
import com.example.sportsFixtures.R
import com.example.sportsFixtures.features.sportsBook.SportBookScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {

    Scaffold(
        topBar = {
            TopAppBarContent()
        }
    ) {
        SportBookScreen(modifier = Modifier.padding(it))
    }

}

@Composable
private fun TopAppBarContent() {
    TopAppBar(
        backgroundColor = Green70
    ) {
        Text(
            modifier = Modifier.padding(spacing.spacing01),
            text = stringResource(id = R.string.app_name),
            style = Typography.titleLarge,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun PreviewTopAppBarContent() {
    TopAppBarContent()
}

