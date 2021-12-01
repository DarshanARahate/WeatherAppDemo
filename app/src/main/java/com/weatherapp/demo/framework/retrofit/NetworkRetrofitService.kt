package com.favortech.myapplication.framework.retrofit


import com.weatherapp.demo.domain.weather.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkRetrofitService {

    //    @GET("forecast?q=nagpur&appid=e82807b473a06c766f5c095abc9c14f8")
    @GET("forecast")
    suspend fun getWeatherApiResponse(
        @Query("q") cityName: String,
        @Query("appid") appid: String
    ): WeatherResponse

////    https://api.github.com/repos/square/okhttp/issues/6905/comments
//    @GET("repos/square/okhttp/issues/{issueId}/comments")
//    suspend fun getComments(@Path("issueId") issueId : Long): CommentsResponse


}