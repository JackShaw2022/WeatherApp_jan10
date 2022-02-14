package com.example.weatherapp_jan10.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    val deg: Int,
    val speed: Double
)