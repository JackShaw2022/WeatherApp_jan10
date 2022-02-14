package com.example.weatherapp_jan10.network.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)