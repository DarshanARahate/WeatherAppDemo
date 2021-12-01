package com.weatherapp.demo.framework

import android.app.Application
import com.favortech.myapplication.framework.CustomViewModelFactory
import com.favortech.myapplication.framework.retrofit.NetworkRetrofitInstance
import com.weatherapp.demo.Interactors
import com.weatherapp.demo.data.weather.WeatherRepository
import com.weatherapp.demo.framework.networkinfo.NetworkInfoUtils
import com.weatherapp.demo.interactors.WeatherUseCases

class MyApplication : Application() {

    lateinit var mCustomViewModelFactory: CustomViewModelFactory


    override fun onCreate() {
        super.onCreate()

        var networkInfoUtils = NetworkInfoUtils(this)
        var networkRetrofitService = NetworkRetrofitInstance.networkService
        var weatherDataSource = WeatherDataSource(networkRetrofitService)
        var weatherRepository = WeatherRepository(weatherDataSource)
        var weatherUseCases = WeatherUseCases(weatherRepository, networkInfoUtils)

        mCustomViewModelFactory = CustomViewModelFactory()
        var interactors = Interactors(weatherUseCases)
        mCustomViewModelFactory.inject(this, interactors)


    }
}