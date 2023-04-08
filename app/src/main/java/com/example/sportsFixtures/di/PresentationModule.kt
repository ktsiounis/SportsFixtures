package com.example.sportsFixtures.di

import com.example.sportsFixtures.features.sportsBook.SportsBookViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::SportsBookViewModel)
}