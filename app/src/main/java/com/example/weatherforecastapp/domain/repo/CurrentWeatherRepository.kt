package com.example.weatherforecastapp.domain.repo

import com.example.weatherforecastapp.API.APIService
import com.example.weatherforecastapp.domain.forecastModel.ForecastModel
import com.example.weatherforecastapp.domain.models.WeatherModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class
CurrentWeatherRepository {

    private val apiService: APIService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
//                The .addConverterFactory(GsonConverterFactory.create()) method is used to
//                configure the converter factory to convert JSON responses into objects using Gson.
//                Gson is a popular JSON parsing library in Java and Kotlin.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(APIService::class.java)
    }


//    The suspend keyword indicates that the function can be used within a coroutine or other asynchronous context.
//    It suggests that the function may perform long-running or blocking operations without blocking the calling thread.
    suspend fun getCurrentWeatherData(q:String, appid:String): WeatherModel=
        apiService.getCurrentWeatherData(q,appid)

    suspend fun getForecastData(q: String, appid: String): ForecastModel=
        apiService.getForecastData(q,appid)

}