package com.weatherapp.demo.framework


import com.favortech.myapplication.framework.retrofit.NetworkRetrofitService
import com.weatherapp.demo.data.weather.IWeatherDataSource
import com.weatherapp.demo.domain.weather.response.WeatherResponse

class WeatherDataSource(private var networkRetrofitService: NetworkRetrofitService) : IWeatherDataSource {

    override suspend fun getWeatherApiResponse(cityName : String, appid: String): WeatherResponse {
       return networkRetrofitService.getWeatherApiResponse(cityName, appid)
    }


}