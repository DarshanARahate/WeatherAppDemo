package com.weatherapp.demo.presentation.weather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.favortech.myapplication.framework.CustomViewModelFactory
import com.weatherapp.demo.R
import com.weatherapp.demo.domain.weather.response.Details
import com.weatherapp.demo.framework.MyApplication
import com.weatherapp.demo.utilities.Constants.API_KEY
import com.weatherapp.demo.utilities.MessageUtility
import com.weatherapp.demo.utilities.Status
import kotlinx.android.synthetic.main.weather_activity.*

class WeatherActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    private lateinit var mViewModel: WeatherViewModel
    private lateinit var mCustomViewModelFactory: CustomViewModelFactory
    private lateinit var mContext : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)
        mContext = this

        mCustomViewModelFactory = (application as MyApplication).mCustomViewModelFactory
        mViewModel = ViewModelProvider(this, mCustomViewModelFactory).get(WeatherViewModel::class.java)

        button_search_city.setOnClickListener {
            mViewModel.getWeatherApiResponse(edittext_search_city.text.toString(), API_KEY)
        }

        mViewModel.mMutableLiveDataResource.observe(this, androidx.lifecycle.Observer {
            when (it.status) {
                Status.LOADING -> {
                    progressbar.visibility = View.VISIBLE
                    MessageUtility.showMessage(it.message, parentLayout1)
                }
                Status.SUCCESS -> {
                    progressbar.visibility = View.GONE
                    MessageUtility.showMessage(it.message, parentLayout1)
                    var hashsetResult : HashMap<String, ArrayList<Details>>? = it.data as? HashMap<String, ArrayList<Details>>
                    var result : String = ""

                    var count = 0
                    hashsetResult?.toSortedMap()?.forEach {

                        if (count != 5) {
                            result = result + "\n" + "------ Weather : ${it.key} ---------------\n"

                            it.value.forEach {
                                result = result + "\n"
                                result = result + "Date and Time :" + it.dtTxt + "\n"
                                result = result + it.weather?.get(0)?.description + "\n"
                                result = result + "Min :" + it.main?.tempMin + "\n"
                                result = result + "Max :" + it.main?.tempMax + "\n"
                            }
                            result = result + "---------------------" + "\n\n"

                        }
                        count = count + 1
                    }
                    textViewResult.text = result
                }
                Status.ERROR -> {
                    progressbar.visibility = View.GONE
                    textViewResult.text = ""
                    MessageUtility.showMessage(it.message, parentLayout1)
                }
            }
        })

    }
}