package com.example.common

fun String?.resolveSportIconFromId(): Int {
    return when(this) {
        "FOOT" -> R.drawable.soccer_24
        "BASK" -> R.drawable.basketball_24
        "TENN", "TABL" -> R.drawable.tennis_24
        "ESPS" -> R.drawable.computer_24
        "HAND" -> R.drawable.handball_24
        "BCHV" -> R.drawable.volleyball_24
        else -> -1
    }
}