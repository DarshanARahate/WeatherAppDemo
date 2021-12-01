package com.weatherapp.demo.domain.weather.response


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("city")
    var city: City?,
    @SerializedName("cnt")
    var cnt: Int?,
    @SerializedName("cod")
    var cod: String?,
    @SerializedName("list")
    var list: List<Details>?,
    @SerializedName("message")
    var message: Int?
)