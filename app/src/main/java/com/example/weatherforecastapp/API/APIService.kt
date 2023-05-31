package com.example.weatherforecastapp.API

import com.example.weatherforecastapp.domain.models.Coord
import com.example.weatherforecastapp.domain.models.Main
import com.example.weatherforecastapp.domain.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("weather")
    suspend fun getCurrentWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String
    ):WeatherModel

    @GET("weather")
    suspend fun fetchWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon:Double
    ): WeatherModel





}

