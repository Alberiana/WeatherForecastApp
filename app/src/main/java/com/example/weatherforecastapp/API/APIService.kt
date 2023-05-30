package com.example.weatherforecastapp.API

import com.example.weatherforecastapp.domain.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("data/2.5/weather")
    fun getCurrentWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String
    ):WeatherModel


}

