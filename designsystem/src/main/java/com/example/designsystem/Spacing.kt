package com.example.designsystem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,

    val spacing0_05: Dp = 4.dp,
    val spacing01: Dp = 8.dp,
    val spacing02: Dp = 16.dp,
    val spacing03: Dp = 24.dp,
    val spacing04: Dp = 32.dp,
    val spacing05: Dp = 40.dp,
    val spacing06: Dp = 48.dp,
    val spacing07: Dp = 56.dp,
    val spacing08: Dp = 64.dp,
    val spacing09: Dp = 80.dp,
    val spacing10: Dp = 96.dp,
    val spacing11: Dp = 112.dp,
    val spacing12: Dp = 128.dp,
)

internal val LocalSpacing = staticCompositionLocalOf { Spacing() }