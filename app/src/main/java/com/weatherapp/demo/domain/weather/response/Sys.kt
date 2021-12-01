package com.weatherapp.demo.domain.weather.response


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    var pod: String?
)