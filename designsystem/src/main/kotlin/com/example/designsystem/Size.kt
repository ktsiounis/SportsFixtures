package com.example.designsystem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizing(
    val default: Dp = 0.dp,
    val xSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val xLarge: Dp = 32.dp,

    val strokeDefault: Dp = 4.dp,
    val borderDefault: Dp = 1.dp,

    /**
     * Custom sizes for specific views
     */
    val sportEventItemWidth: Dp = 90.dp
)

internal val LocalSizing = staticCompositionLocalOf { Sizing() }
