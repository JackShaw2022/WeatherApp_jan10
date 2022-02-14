package com.example.weatherapp_jan10.network.repository

import com.example.weatherapp_jan10.network.service.WeatherService
import com.example.weatherapp_jan10.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {

    fun getWeatherForCity(cityName: String) = flow {
        emit(Resource.Loading)
        val response = weatherService.getWeatherForCity(cityName)
        val resource = try {
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Api call was not successful.")
            }
        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "Unexpected error")
        }
        emit(resource)
    }

}