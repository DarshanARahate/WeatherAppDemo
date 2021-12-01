package com.weatherapp.demo.domain.weather.response


import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("clouds")
    var clouds: Clouds?,
    @SerializedName("dt")
    var dt: Int?,
    @SerializedName("dt_txt")
    var dtTxt: String?,
    @SerializedName("main")
    var main: Main?,
    @SerializedName("pop")
    var pop: Double?,
    @SerializedName("sys")
    var sys: Sys?,
    @SerializedName("visibility")
    var visibility: Int?,
    @SerializedName("weather")
    var weather: List<Weather>?,
    @SerializedName("wind")
    var wind: Wind?
)