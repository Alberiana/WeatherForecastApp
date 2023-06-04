package com.example.weatherforecastapp.API

import com.example.weatherforecastapp.domain.forecastModel.ForecastModel
import com.example.weatherforecastapp.domain.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("weather")
    suspend fun getCurrentWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String
    ):WeatherModel


    @GET("forecast")
    suspend fun getForecastData(
        @Query("q") q: String,
        @Query("appid") appid:String
    ): ForecastModel





}

