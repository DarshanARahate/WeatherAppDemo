package com.weatherapp.demo.data.weather

class WeatherRepository(var mIWeatherDataSource : IWeatherDataSource) {

    suspend fun getWeatherApiResponse(cityName : String, appid: String) = mIWeatherDataSource.getWeatherApiResponse(cityName, appid)

}