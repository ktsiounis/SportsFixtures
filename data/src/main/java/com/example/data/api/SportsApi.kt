package com.example.data.api

import retrofit2.Call
import retrofit2.http.GET

interface SportsApi {

    companion object {
        private const val SPORTS_LIST = "sports"
    }

    @GET(SPORTS_LIST)
    fun getSports(): Call<List<SportRaw>>

}