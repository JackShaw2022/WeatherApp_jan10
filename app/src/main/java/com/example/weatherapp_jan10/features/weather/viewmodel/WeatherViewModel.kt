package com.example.weatherapp_jan10.features.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_jan10.network.models.WeatherResponse
import com.example.weatherapp_jan10.network.repository.WeatherRepository
import com.example.weatherapp_jan10.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _weather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val weather: LiveData<Resource<WeatherResponse>> get() = _weather

    fun getWeatherForCity(cityName: String) {
        viewModelScope.launch {
            weatherRepository.getWeatherForCity(cityName).collect { resource ->
                _weather.postValue(resource)
            }
        }
    }

}