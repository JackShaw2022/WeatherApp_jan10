package com.example.weatherapp_jan10.network.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Sys(
    val country: String,
    val id: Int,
    val message: Double?,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)