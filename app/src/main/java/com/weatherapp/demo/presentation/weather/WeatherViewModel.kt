package com.weatherapp.demo.presentation.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.demo.Interactors
import com.weatherapp.demo.utilities.Utils
import kotlinx.coroutines.launch

class WeatherViewModel(var interactors: Interactors) : ViewModel() {

    private val TAG = javaClass.simpleName
    val mMutableLiveDataResource: MutableLiveData<Utils.Resource<Any>> = MutableLiveData()


    fun getWeatherApiResponse(cityName: String, appid: String) = viewModelScope.launch {

        interactors.weatherUseCases.getWeatherApiResponse(cityName, appid, mMutableLiveDataResource)

    }

}