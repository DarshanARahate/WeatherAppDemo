package com.weatherapp.demo.data.weather

import com.weatherapp.demo.domain.weather.response.WeatherResponse

interface IWeatherDataSource {

    suspend fun getWeatherApiResponse(cityName : String, appid: String) : WeatherResponse

}