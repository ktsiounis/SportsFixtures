# Welcome to SportsFixtures! :tada:

## Introduction :hand:
SportsFixtures is a simple, Single Activity, Compose app where as a user, you can find upcoming (or recently completed) sport events and be aware of their starting time. Each sport event, has a countdown timer so you can easily know when it starts and you are also able to make events favorite to find them easier at the start of each row :wink:
Take a sneak pick below in [Quick Video Preview Section](#quick-video-preview-movie-camera)

## Technical details :man_technologist:
Let's get into the juicy details :nerd_face:. This app is developed using the below:
 - Jetpack Compose for the UI part.
 - Material 3 for the icon and the styling.
 - MVVM/MVI as architecture with clean architecture.
 - Retrofit and GSON for the API calls.
 - Kotlin Flows for asychronous components.
 - Koin for DI.
 - Modularization (more details about the modules below).
 
 The app is broken down to modules. Those are:
 - **`common`**: This module is used for common classes or extension functions that can be used across all the modules.
 - **`core`**: This module holds the core of the MVI architecture, with a CoreViewModel and can be extended from all the VMs in the app.
 - **`data`**: This module holds the repository and and ApiClient along with the DataModels used for parsing the Api response
 - **`designsystem`**: This module includes the Design System of the app, meaning that holds Spacing, Sizing, Typography, Color and the Theme and it can also be extended holding common composables that can be used across the whole app.
 - **`domain`**: This module includes the business logic and especially the UseCases that use the repositories from data layer to retrieve data.
 - **`network`**: This modules is used for network related stuff and for now is used for an ApiProvider that can be used in the future for more complex APIs, with different environments. It also includes Result convertes and extension funtions for converting api response to Result.

## Quick Video Preview :movie_camera:
https://user-images.githubusercontent.com/10981693/230789361-be8a8e13-633b-4a5e-a55b-65d0b3eefbbc.mp4

## Testing :test_tube:
For the Unit testing I used [Mockk](https://mockk.io/) for mocking dependencies, [Turbine](https://github.com/cashapp/turbine) for Flows testing and [kotest](https://github.com/kotest/kotest) for more comprehensive matchers.

For the UI tests, I simply used Compose UI Testing which is similar to Espresso.
