package com.example.weatherforecastapp.domain.repo

import com.example.weatherforecastapp.API.APIService
import com.example.weatherforecastapp.domain.models.Coord
import com.example.weatherforecastapp.domain.models.WeatherModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrentWeatherRepository {

    private val apiService: APIService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(APIService::class.java)
    }


    suspend fun getCurrentWeatherData(q:String, appid:String): WeatherModel=
        apiService.getCurrentWeatherData(q,appid)

    suspend fun fetchWeatherData(lat: Double, lon: Double): WeatherModel=
        apiService.fetchWeatherData(lat,lon)

}