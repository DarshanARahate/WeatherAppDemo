package com.favortech.myapplication.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.demo.Interactors
import com.weatherapp.demo.presentation.weather.WeatherViewModel

class CustomViewModelFactory : ViewModelProvider.Factory {

    lateinit var mApplication: Application
    lateinit var mInteractors: Interactors

    fun inject(application: Application, interactors: Interactors) {
        mApplication = application
        mInteractors = interactors
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(WeatherViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Interactors::class.java).newInstance(mInteractors)
        }  else {
            throw IllegalStateException("ViewModel must extend")
        }
    }
}