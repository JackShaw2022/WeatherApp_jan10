package com.example.weatherapp_jan10.network.service

import com.example.weatherapp_jan10.network.models.WeatherResponse
import com.example.weatherapp_jan10.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getWeatherForCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "imperial"
    ): Response<WeatherResponse>
}