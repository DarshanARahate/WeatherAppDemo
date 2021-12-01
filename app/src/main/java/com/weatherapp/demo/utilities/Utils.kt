package com.weatherapp.demo.utilities

import com.weatherapp.demo.utilities.Status.ERROR
import com.weatherapp.demo.utilities.Status.LOADING
import com.weatherapp.demo.utilities.Status.SUCCESS

class Utils {

    data class Resource<out T>(
        val status: Int,
        val data: T?,
        val message: String?
    ) {
        companion object {
            fun <T> success(data: T): Resource<T> =
                Resource(status = SUCCESS, data = data, message = null)

            fun <T> error(data: T?, message: String): Resource<T> =
                Resource(status = ERROR, data = data, message = message)

            fun <T> loading(data: T?): Resource<T> =
                Resource(status = LOADING, data = data, message = null)
        }
    }
}