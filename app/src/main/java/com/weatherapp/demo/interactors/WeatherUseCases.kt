package com.weatherapp.demo.interactors

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.weatherapp.demo.data.weather.WeatherRepository
import com.weatherapp.demo.domain.weather.response.Details
import com.weatherapp.demo.domain.weather.response.WeatherResponse
import com.weatherapp.demo.framework.networkinfo.NetworkInfoUtils
import com.weatherapp.demo.utilities.Constants.VALUE_PROCESS_API_SUCCESS_RESPONSE
import com.weatherapp.demo.utilities.Constants.VALUE_PROCESS_NO_INTERNET_CONNECTION
import com.weatherapp.demo.utilities.Constants.VALUE_PROCESS_PROCESS_STARTED
import com.weatherapp.demo.utilities.Status
import com.weatherapp.demo.utilities.Utils
import java.text.SimpleDateFormat

class WeatherUseCases(
    private var mWeatherRepository: WeatherRepository,
    private val networkInfoUtils: NetworkInfoUtils
) {

    private val TAG = javaClass.simpleName

    suspend fun getWeatherApiResponse(
        cityName: String,
        appid: String,
        mutableLiveDataResource: MutableLiveData<Utils.Resource<Any>>
    ) {
        if (networkInfoUtils.isInternetAvailable()) {
            try {
                mutableLiveDataResource.value = Utils.Resource(
                    Status.LOADING,
                    null,
                    VALUE_PROCESS_PROCESS_STARTED
                )
                mutableLiveDataResource.value = Utils.Resource(
                    Status.SUCCESS,
                    getWeatherFinalResult(mWeatherRepository.getWeatherApiResponse(cityName, appid)),
                    VALUE_PROCESS_API_SUCCESS_RESPONSE
                )
            } catch (e: Exception) {
                e.printStackTrace()
                mutableLiveDataResource.value = Utils.Resource(Status.ERROR, null, e.message)
            }
        } else {
            mutableLiveDataResource.value = Utils.Resource(
                Status.ERROR,
                null,
                VALUE_PROCESS_NO_INTERNET_CONNECTION
            )
        }
    }

    fun getWeatherFinalResult(weatherApiResponse: WeatherResponse) : HashMap<String, ArrayList<Details>> {
        var hashset = HashSet<String>()
        var hashsetResult = HashMap<String, ArrayList<Details>>()
        val fmt1 = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val fmt2 = SimpleDateFormat("yyyy-MM-dd")
        weatherApiResponse.list?.forEach {
            var date = fmt1.parse(it.dtTxt!!)
            hashset.add(fmt2.format(date))
        }
        hashset.sorted()
        weatherApiResponse.list?.forEach {
            var date = fmt1.parse(it.dtTxt!!)
            if (hashset.contains(fmt2.format(date))) {
               var values : ArrayList<Details>? = hashsetResult.get(fmt2.format(date)) as? ArrayList<Details>
                if (values != null) {
                    values.add(it)
                    hashsetResult.set(fmt2.format(date), values)
                } else {
                    var arr = ArrayList<Details>()
                    arr.add(it)
                    hashsetResult.set(fmt2.format(date), arr)
                }
            }
        }
        return hashsetResult
    }


}